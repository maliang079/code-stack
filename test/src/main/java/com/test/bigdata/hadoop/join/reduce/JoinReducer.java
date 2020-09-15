package com.test.bigdata.hadoop.join.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<JoinKey, JoinValue, Text, NullWritable> {

    private Text k = new Text();

    @Override
    protected void reduce(JoinKey key, Iterable<JoinValue> values, Context context) throws IOException, InterruptedException {
        Iterator<JoinValue> it = values.iterator();
        String name = "";
        while (it.hasNext()) {
            JoinValue value = it.next();
            if (key.getFlag() == 0) {
                name = value.getOther();
            } else if (key.getFlag() == 1) {
                k.set(StringUtils.joinWith(",", name, key.getCode(), value.getOther()));
                context.write(k, NullWritable.get());
            }
        }
    }
}
