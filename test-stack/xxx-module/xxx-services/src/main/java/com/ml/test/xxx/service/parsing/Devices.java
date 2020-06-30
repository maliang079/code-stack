package com.ml.test.xxx.service.parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mal on 2019/7/5.
 */
@Getter
@AllArgsConstructor
public enum Devices {

    FIRE_HYDRANT("C", "室外消火栓"),
    PIPE_PRESSURE("D", "管道压力"),
    WATER_TANK_LEVEL_PRESSURE("E", "水箱液位压力"),
    UNKNOWN("UNKNOWN", "未知设备");

    private String code;

    private String name;

    public static Devices fromStr(String type) {
        if (StringUtils.equalsIgnoreCase(type, FIRE_HYDRANT.getCode())) {
            return FIRE_HYDRANT;
        } else if (StringUtils.equalsIgnoreCase(type, PIPE_PRESSURE.getCode())) {
            return PIPE_PRESSURE;
        } else if (StringUtils.equalsIgnoreCase(type, WATER_TANK_LEVEL_PRESSURE.getCode())) {
            return WATER_TANK_LEVEL_PRESSURE;
        }  else {
            return UNKNOWN;
        }
    }

}
