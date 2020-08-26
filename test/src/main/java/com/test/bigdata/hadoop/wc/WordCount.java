package com.test.bigdata.hadoop.wc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCount.class);

        job.setMapperClass(MapperTask.class);
        job.setReducerClass(ReducerTask.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("E:\\WorkSpace\\GitWorkSpace\\code-stack\\test\\data\\wordcount\\input\\input.txt"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\WorkSpace\\GitWorkSpace\\code-stack\\test\\data\\wordcount\\output"));

        boolean result = job.waitForCompletion(true);
        System.out.println(result);

    }

}
