package com.test.bigdata.hadoop.join.reduce;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class JoinSC extends WritableComparator {

    public JoinSC() {
        super(JoinKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        int retVal = 0;
        JoinKey k1 = (JoinKey) a;
        JoinKey k2 = (JoinKey) b;
        retVal = k1.getCode().compareTo(k2.getCode());
        if (retVal == 0) retVal = k1.getFlag().compareTo(k2.getFlag());
        return retVal;
    }
}
