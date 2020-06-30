package com.ml.test.xxx.service.parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mal on 2019/7/5.
 */
@Getter
@AllArgsConstructor
public enum Units {

    KPA(1, "KPA"),
    MPA(1000, "MPA"),
    METRE(10, "米"),
    UNKNOWN(-1, "未知单位");

    private int factors;

    private String unit;

    public static Units fromDevices(Devices devices) {
        switch(devices) {
            case FIRE_HYDRANT:
            case PIPE_PRESSURE:
                return MPA;
            case WATER_TANK_LEVEL_PRESSURE:
                return METRE;
            default:
                return UNKNOWN;
        }
    }

}
