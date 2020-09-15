package com.test.bigdata.hadoop.join.reduce;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class JoinMapper extends Mapper<LongWritable, Text, JoinKey, JoinValue> {

    private Integer flag;

    private JoinKey k = new JoinKey();
    private JoinValue v = new JoinValue();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        flag = StringUtils.endsWith(fileSplit.getPath().getName(), "code.txt") ? 0 : 1;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = StringUtils.split(value.toString(), ",");
        if (flag == 0) {
            k.setCode(fields[0]);
            v.setCode(fields[0]);
            v.setOther(fields[1]);
        } else {
            k.setCode(fields[1]);
            v.setCode(fields[1]);
            v.setOther(StringUtils.joinWith(",", fields[0], fields[2]));
        }
        k.setFlag(flag);

        context.write(k, v);
    }

}
