package com.test.bigdata.hadoop.topN;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ReducerKeyGroup extends WritableComparator {

    private SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");

    public ReducerKeyGroup() {
        super(YMDT.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        int retVal = 0;
        YMDT ymt1 = (YMDT) a;
        YMDT ymt2 = (YMDT) b;

        try {
            Date ymd1 = ymd.parse(ymt1.getYmd());
            Date ymd2 = ymd.parse(ymt2.getYmd());

            retVal = ym.format(ymd1).compareTo(ym.format(ymd2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
