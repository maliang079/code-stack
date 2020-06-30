package com.ml.test.xxx.service.parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mal on 2019/7/4.
 */
public class Signs {

    @lombok.Getter
    @AllArgsConstructor
    public enum BitOne {

        NORMAL(0, "正常"),
        LEAK_WATER(1, "漏水"),
        DRAIN_COCK_ACTION(2, "放水阀动作"),
        SENSOR_FAULT(8, "放水传感器故障"),
        UNKNOWN(-1, "未知标志位");

        private int code;
        private String remark;

        public static BitOne fromInt(int value) {
            switch(value) {
                case 0: return NORMAL;
                case 1: return LEAK_WATER;
                case 2: return DRAIN_COCK_ACTION;
                case 8: return SENSOR_FAULT;
                default: return UNKNOWN;
            }
        }

    }

    @Getter
    @AllArgsConstructor
    public enum BitTow {

        NORMAL(0, "正常"),
        IMPACT_ALARM(1, "撞击报警"),
        OPEN_COVER_ALARM(2, "开盖报警"),
        SENSOR_FAULT(8, "撞击传感器故障"),
        UNKNOWN(-1, "未知标志位");

        private int code;
        private String remark;

        public static BitTow fromInt(int value) {
            switch(value) {
                case 0: return NORMAL;
                case 1: return IMPACT_ALARM;
                case 2: return OPEN_COVER_ALARM;
                case 8: return SENSOR_FAULT;
                default: return UNKNOWN;
            }
        }

    }

    @Getter
    @AllArgsConstructor
    public enum BitThree {

        NORMAL(0, "压力正常"),
        BELOW_LOWER_LIMIT(1, "压力低于下限阀值"),
        ABOVE_UPPER_LIMIT(2, "压力高于上限阀值"),
        SENSOR_FAULT(4, "压力传感器故障"),
        UNKNOWN(-1, "未知标志位");

        private int code;
        private String remark;

        public static BitThree fromInt(int value) {
            switch(value) {
                case 0: return NORMAL;
                case 1: return BELOW_LOWER_LIMIT;
                case 2: return ABOVE_UPPER_LIMIT;
                case 4: return SENSOR_FAULT;
                default: return UNKNOWN;
            }
        }

    }

    @Getter
    @AllArgsConstructor
    public enum BitFour {

        NORMAL(0, "正常"),
        BATTERY_VOLTAGE_LOW (1, "电池电压低"),
        DEVICE_LOSS(4, "子设备失联"),
        UNKNOWN(-1, "未知标志位");

        private int code;
        private String remark;

        public static BitFour fromInt(int value) {
            switch(value) {
                case 0: return NORMAL;
                case 1: return BATTERY_VOLTAGE_LOW;
                case 4: return DEVICE_LOSS;
                default: return UNKNOWN;
            }
        }

    }

}
