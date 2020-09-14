package com.test.bigdata.hadoop.topN;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CombinerKeyGroup extends WritableComparator {

    public CombinerKeyGroup() {
        super(YMDT.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        YMDT ymt1 = (YMDT) a;
        YMDT ymt2 = (YMDT) b;

        return ymt1.getYmd().compareTo(ymt2.getYmd());
    }
}
