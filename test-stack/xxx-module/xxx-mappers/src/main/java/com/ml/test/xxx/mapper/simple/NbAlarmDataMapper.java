package com.ml.test.xxx.mapper.simple;

import java.util.List;

import com.ml.test.xxx.model.simple.NbAlarmData;


public interface NbAlarmDataMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NbAlarmData record);

    NbAlarmData selectByPrimaryKey(Long id);

    List<NbAlarmData> selectAll();

    int updateByPrimaryKey(NbAlarmData record);

}