package com.test.bigdata.hadoop.group.comparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class GCDriver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(GCDriver.class);

        job.setMapperClass(GCMapper.class);
        job.setReducerClass(GCReducer.class);

        job.setMapOutputKeyClass(Product.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Product.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(GC.class);

        FileInputFormat.setInputPaths(job, new Path("E:\\WorkSpace\\GitWorkSpace\\code-stack\\test\\data\\gc\\input\\input.txt"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\WorkSpace\\GitWorkSpace\\code-stack\\test\\data\\gc\\output"));

        boolean result = job.waitForCompletion(true);
        System.out.println(result);
    }

}
