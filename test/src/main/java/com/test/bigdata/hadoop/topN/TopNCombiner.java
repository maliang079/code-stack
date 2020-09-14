package com.test.bigdata.hadoop.topN;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class TopNCombiner extends Reducer<YMDT, IntWritable, YMDT, IntWritable> {

    private IntWritable v = new IntWritable();

    @Override
    protected void reduce(YMDT key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int maxT = 0;
        for (IntWritable t : values) {
            if (maxT < t.get()) maxT = t.get();
        }
        key.setT(maxT);
        v.set(maxT);
        context.write(key, v);
    }

}
