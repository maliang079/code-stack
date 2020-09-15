package com.test.bigdata.hadoop.topN;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TopN {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.setInt("topn.nums", 2);

        Job job = Job.getInstance(conf);
        job.setJarByClass(TopN.class);

        // 设置map相关
        FileInputFormat.setInputPaths(job, new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\topN\\input"));
        job.setMapperClass(TopNMapper.class);
        job.setPartitionerClass(TopNPartitioner.class);
        job.setMapOutputKeyClass(YMDT.class);
        job.setMapOutputValueClass(IntWritable.class);
        // map combiner优化相关
        /*job.setCombinerKeyGroupingComparatorClass(CombinerKeyGroup.class);
        job.setCombinerClass(TopNCombiner.class);*/


        // 设置reduce相关
        job.setReducerClass(TopNReducer.class);
        job.setGroupingComparatorClass(ReducerKeyGroup.class);
        job.setOutputKeyClass(YMDT.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setNumReduceTasks(2);
        FileOutputFormat.setOutputPath(job, new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\topN\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
    }

}
