package com.ml.test.xxx.model.simple;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NbAlarmData {

    private Long id;

    private Long nbRdId;

    private Timestamp alarmStartTime;

    private Timestamp alarmEndTime;

    private Timestamp createTime;

    private Timestamp updateTime;

}