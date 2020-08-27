package com.test.bigdata.hadoop.group.comparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Product implements WritableComparable {

    private int orderNo;
    private double money;

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public int compareTo(Object o) {
        Product p = (Product) o;
        int result = 0;
        if (orderNo > p.getOrderNo()) {
            result = 1;
        } else if (orderNo < p.getOrderNo()) {
            result = -1;
        } else {
            if (money > p.getMoney()) {
                result = -1;
            } else if (money < p.getMoney()) {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(orderNo);
        dataOutput.writeDouble(money);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        orderNo = dataInput.readInt();
        money = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return orderNo + "," + money;
    }
}
