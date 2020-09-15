package com.test.bigdata.hadoop.join.reduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Join {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        FileInputFormat.setInputPaths(job, new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\join\\input"));
        job.setMapperClass(JoinMapper.class);
        job.setPartitionerClass(JoinPartitioner.class);
        job.setSortComparatorClass(JoinSC.class);
        job.setMapOutputKeyClass(JoinKey.class);
        job.setMapOutputValueClass(JoinValue.class);

        job.setGroupingComparatorClass(JoinGC.class);
        job.setReducerClass(JoinReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileOutputFormat.setOutputPath(job, new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\join\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
    }

}
