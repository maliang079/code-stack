package com.test.bigdata.hadoop.topN;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.WritableComparable;

public class YMDT implements WritableComparable<YMDT> {

    private String ymd;
    private Integer t;

    @Override
    public int compareTo(YMDT o) {
        int retVal = 0;
        SimpleDateFormat ydm = new SimpleDateFormat("yyy-MM-dd");
        SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");
        try {
            Date ymd1 = ydm.parse(this.ymd);
            Date ymd2 = ydm.parse(o.ymd);

            Date ym1 = ym.parse(ym.format(ymd1));
            Date ym2 = ym.parse(ym.format(ymd2));

            retVal = ym1.compareTo(ym2) * -1;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (retVal == 0) {
            retVal = this.t.compareTo(o.t) * -1;
        }

        return retVal;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(ymd);
        out.writeInt(t);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.ymd = in.readUTF();
        this.t = in.readInt();
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ym) {
        this.ymd = ym;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return ymd + "\t" + t;
    }
}
