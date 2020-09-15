package com.test.bigdata.hadoop.join.reduce;

import org.apache.hadoop.mapreduce.Partitioner;

public class JoinPartitioner extends Partitioner<JoinKey, JoinValue> {
    @Override
    public int getPartition(JoinKey joinKey, JoinValue joinValue, int numPartitions) {
        return joinKey.getCode().hashCode() & Integer.MAX_VALUE % numPartitions;
    }
}
