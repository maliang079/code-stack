package com.test.bigdata.hadoop.join.reduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class JoinValue implements Writable {

    private String code;
    private String other;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(code);
        out.writeUTF(other);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.code = in.readUTF();
        this.other = in.readUTF();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
