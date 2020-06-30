package com.ml.rabbitmq.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.SystemClock;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.instrumentation.SourceCounter;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitMQSource extends AbstractSource implements
        EventDrivenSource, Configurable {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQSource.class);

    private volatile AtomicBoolean isStop;
    private volatile SourceCounter sourceCounter;
    private volatile ExecutorService executor;
    private volatile ChannelProcessor channelProcessor;

    private volatile String userName;
    private volatile String password;
    private volatile String host;
    private volatile Integer basicQos;
    private volatile Boolean autoAck;
    private volatile String queue;
    private volatile Integer deliveryTimeOut;
    private volatile Integer bufferCount;
    private volatile Long batchTimeout;

    private volatile Connection conn;
    private volatile Channel cnl;
    private volatile QueueingConsumer consumer;

    @Override
    public void configure(Context context) {
        try {
            userName = context.getString(RabbitMQSourceConfigurationConstants.CONFIG_USER_NAME, StringUtils.EMPTY);
            password = context.getString(RabbitMQSourceConfigurationConstants.CONFIG_PASSWORD, StringUtils.EMPTY);

            queue = context.getString(RabbitMQSourceConfigurationConstants.CONFIG_QUEUE_NAME);
            Preconditions.checkState(queue != null && !queue.isEmpty(),
                    "RabbitMQSource queue name specified is empty");
            Preconditions.checkNotNull(queue,
                    "RabbitMQSource requires a RabbitMQSource queue name to be specified");

            host = context.getString(RabbitMQSourceConfigurationConstants.CONFIG_HOST);
            Preconditions.checkState(host != null && !host.isEmpty(),
                    "RabbitMQSource host specified is empty");
            Preconditions.checkNotNull(host,
                    "RabbitMQSource requires a RabbitMQSource host to be specified");

            basicQos = context.getInteger(RabbitMQSourceConfigurationConstants.CONFIG_BASIC_QOS, 1);
            deliveryTimeOut = context.getInteger(RabbitMQSourceConfigurationConstants.CONFIG_DELIVERY_TIMEOUT, 100);
            autoAck = context.getBoolean(RabbitMQSourceConfigurationConstants.CONFIG_AUTO_ACK, true);
            bufferCount = context.getInteger(RabbitMQSourceConfigurationConstants.CONFIG_BUFFER_COUNT, 10);
            batchTimeout = context.getLong(RabbitMQSourceConfigurationConstants.CONFIG_BATCH_TIMEOUT, 5000l);
          } catch (Exception e) {
            LOG.error("Error configuring RabbitMQSource!", e);
            Throwables.propagate(e);
          }
          if (sourceCounter == null) {
            sourceCounter = new SourceCounter(getName());
          }
    }

    @Override
    public void start() {
        LOG.debug("RabbitMQSource starting...");
        try {
            isStop = new AtomicBoolean(false);
            channelProcessor = getChannelProcessor();

            ConnectionFactory confactory = new ConnectionFactory();
            confactory.setUsername(userName);
            confactory.setPassword(password);
            confactory.setAutomaticRecoveryEnabled(true);
            conn = confactory.newConnection(createAddress());
            cnl = conn.createChannel();
            cnl.basicQos(basicQos);
            consumer = new QueueingConsumer(cnl);
            cnl.basicConsume(queue, autoAck, consumer);

            executor = Executors.newSingleThreadExecutor();
            executor.submit(new Consumer());

            sourceCounter.start();
            super.start();

        } catch (IOException e) {
            LOG.error("Error start RabbitMQSource!", e);
            Throwables.propagate(e);
        } catch (TimeoutException e) {
            LOG.error("Error start RabbitMQSource!", e);
            Throwables.propagate(e);
        }
        LOG.debug("RabbitMQSource started");
    }

    private Address[] createAddress() {
        List<Address> adds = new ArrayList<Address>();
        String[] hosts = StringUtils.split(host, ",");
        for (String item : hosts) {
            String[] ipAndPort = StringUtils.split(item, ":");
            adds.add(new Address(ipAndPort[0], Integer.valueOf(ipAndPort[1])));
        }
        return adds.toArray(new Address[hosts.length]);
    }

    @Override
    public void stop() {
        LOG.info("Stopping RabbitMQSource.");
        isStop.compareAndSet(false, true);
        executor.shutdown();

        while (!executor.isTerminated()) {
          LOG.debug("Waiting for exec executor service to stop");
          try {
            executor.awaitTermination(500, TimeUnit.MILLISECONDS);
          } catch (InterruptedException e) {
            LOG.debug("Interrupted while waiting for exec executor service "
                + "to stop. Just exiting.");
            Thread.currentThread().interrupt();
          }
        }

        sourceCounter.stop();
        super.stop();

        LOG.debug("RabbitMQSource stopped. Metrics:{}", sourceCounter);
    }

    private class Consumer implements Runnable {

        private SystemClock systemClock = new SystemClock();
        private Long lastPushToChannel = systemClock.currentTimeMillis();

        private void flushEventBatch(List<Event> eventList) {
            channelProcessor.processEventBatch(eventList);
            sourceCounter.addToEventAcceptedCount(eventList.size());
            eventList.clear();
            lastPushToChannel = systemClock.currentTimeMillis();
        }

        private boolean timeout(){
            return (systemClock.currentTimeMillis() - lastPushToChannel) >= batchTimeout;
        }

        @Override
        public void run() {
            List<Event> eventList = new ArrayList<Event>();
            while (!isStop.get() && !Thread.interrupted()) {
                QueueingConsumer.Delivery delivery;
                try {
                    delivery = consumer.nextDelivery(deliveryTimeOut);
                    if (delivery != null) {
                        sourceCounter.incrementEventReceivedCount();
                        byte[] data = delivery.getBody();
                        if (data == null) {
                            LOG.warn("delivery body is empty.");
                            continue;
                        }
                        eventList.add(EventBuilder.withBody(data));
                        if(eventList.size() >= bufferCount || timeout()) {
                          flushEventBatch(eventList);
                          sourceCounter.addToEventAcceptedCount(eventList.size());
                        }
                    } else {
                        if(!eventList.isEmpty()) {
                            flushEventBatch(eventList);
                            sourceCounter.addToEventAcceptedCount(eventList.size());
                        }
                    }
                } catch (ShutdownSignalException e) {
                    LOG.error("Rabbitmq connection is shutdown!", e);
                } catch (InterruptedException e) {
                    LOG.debug("Consumer to stop. Just exiting.");
                } catch (Exception e) {
                    LOG.error("Rabbitmq connection is shutdown!", e);
                }
            }
        }
    }

}
