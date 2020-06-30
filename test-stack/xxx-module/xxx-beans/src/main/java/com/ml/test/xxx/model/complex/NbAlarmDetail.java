package com.ml.test.xxx.model.complex;

import lombok.Data;

/**
 * Created by mal on 2019/7/12.
 */
@Data
public class NbAlarmDetail {

    private Long id;

    private String alarmStartTime;

    private String alarmEndTime;

    private String signBitOneRemark;

    private String signBitTowRemark;

    private String signBitThreeRemark;

    private String signBitFourRemark;

    private String deviceName;

    private String deviceInsDes;

    private String placeName;

    private String placeDetail;

    private String fireSysName;

    private String projectName;

}
