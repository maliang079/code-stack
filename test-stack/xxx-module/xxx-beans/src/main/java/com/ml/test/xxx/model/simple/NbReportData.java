package com.ml.test.xxx.model.simple;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NbReportData {

    private Long id;

    private Long projectId;

    private String type;

    private String name;

    private String imei;

    private String pressure;

    private String voltage;

    private int signBitOne;

    private String signBitOneRemark;

    private int signBitTow;

    private String signBitTowRemark;

    private int signBitThree;

    private String signBitThreeRemark;

    private int signBitFour;

    private String signBitFourRemark;

    private String originData;

    private Timestamp createTime;

    private Timestamp updateTime;

}