package com.test.bigdata.hadoop.join.map;

import java.io.IOException;
import java.net.URI;

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

        URI uri = new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\join\\input\\code.txt").toUri();
        job.setCacheFiles(new URI[] {uri});

        FileInputFormat.setInputPaths(job, new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\join\\input\\input.txt"));
        job.setMapperClass(JoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);
        FileOutputFormat.setOutputPath(job, new Path("D:\\WorkSpace\\GitWorkSpace\\code-stack\\code-stack\\test\\data\\join\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
    }

}
