package com.test.bigdata.hadoop.topN;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopNMapper extends Mapper<LongWritable, Text, YMDT, IntWritable> {

    private YMDT key = new YMDT();
    private IntWritable value = new IntWritable();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = StringUtils.split(line, ",");

        this.key.setYmd(fields[0]);
        int t = Integer.valueOf(fields[2]);
        this.key.setT(t);
        this.value.set(t);
        context.write(this.key, this.value);
    }

}
