package com.test.bigdata.hadoop.join.reduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class JoinGC extends WritableComparator {

    public JoinGC() {
        super(JoinKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        JoinKey k1 = (JoinKey) a;
        JoinKey k2 = (JoinKey) b;
        return k1.getCode().compareTo(k2.getCode());
    }
}
