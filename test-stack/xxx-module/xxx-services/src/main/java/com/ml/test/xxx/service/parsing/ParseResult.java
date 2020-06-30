package com.ml.test.xxx.service.parsing;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mal on 2019/7/5.
 */
@Getter
@Setter
public class ParseResult {

    /** 原始数据 */
    private String originData;

    /** 设备类型 */
    private Devices devices;

    /** 设备号 */
    private String imei;

    /** 压力 */
    private String pressure;

    /** 电压 */
    private String voltage;

    /** 标志位 */
    private String signs;

    /** 校验和 */
    private String checkSum;

    /** 标志位1 */
    private Signs.BitOne bitOne;

    /** 标志位2 */
    private Signs.BitTow bitTow;

    /** 标志位3 */
    private Signs.BitThree bitThree;

    /** 标志位4 */
    private Signs.BitFour bitFour;

}
