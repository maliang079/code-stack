package com.test.bigdata.hadoop.topN;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class TopNPartitioner extends Partitioner<YMDT, IntWritable> {

    private SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");

    @Override
    public int getPartition(YMDT key, IntWritable value, int numPartitions) {
        int retVal = 0;
        try {
            String strYm = ym.format(ymd.parse(key.getYmd()));
            retVal = strYm.hashCode() & Integer.MAX_VALUE % numPartitions;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retVal;
    }

}
