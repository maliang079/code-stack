package com.test.bigdata.hadoop.group.comparator;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class GCReducer extends Reducer<Product, NullWritable, Product, NullWritable> {
    @Override
    protected void reduce(Product key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable nw : values) {
            context.write(key, NullWritable.get());
        }
    }
}
