package com.test.bigdata.hadoop.group.comparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GC extends WritableComparator {

    public GC() {
        super(Product.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Product ap = (Product) a;
        Product bp = (Product) b;
        return ap.getOrderNo() > bp.getOrderNo() ? 1 : ap.getOrderNo() < bp.getOrderNo() ? -1 : 0;
    }
}
