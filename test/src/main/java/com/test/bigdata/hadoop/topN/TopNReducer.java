package com.test.bigdata.hadoop.topN;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class TopNReducer extends Reducer<YMDT, IntWritable, YMDT, NullWritable> {

    private int topN = 0;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        topN = context.getConfiguration().getInt("topn.nums", 2);
    }

    @Override
    protected void reduce(YMDT key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int tmpTopN = 0;

        Date preYmd = null;
        for (IntWritable t : values) {
            try {
                if (tmpTopN > 0) {
                    Date ymd = sdf.parse(key.getYmd());
                    if (preYmd.compareTo(ymd) == 0)
                        continue;
                }

                if (tmpTopN >= topN) break;
                context.write(key, NullWritable.get());
                tmpTopN++;
                preYmd = sdf.parse(key.getYmd());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
