package com.test.bigdata.hadoop.group.comparator;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GCMapper extends Mapper<LongWritable, Text, Product, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] strs = line.split(",");
        Product p = new Product();
        p.setOrderNo(Integer.valueOf(strs[0]));
        p.setMoney(Double.valueOf(strs[1]));
        context.write(p, NullWritable.get());
    }
}
