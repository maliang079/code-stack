package com.test.bigdata.hadoop.topN;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class TopNReducer extends Reducer<YMDT, IntWritable, YMDT, NullWritable> {

    private int topN = 0;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        topN = context.getConfiguration().getInt("topn.nums", 2);
    }

    @Override
    protected void reduce(YMDT key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int tmpTopN = 0;
        for (IntWritable t : values) {
            if (tmpTopN >= topN) break;
            context.write(key, NullWritable.get());
            tmpTopN++;
        }
    }
}
