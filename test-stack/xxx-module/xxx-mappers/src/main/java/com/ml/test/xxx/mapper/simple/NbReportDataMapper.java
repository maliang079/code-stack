package com.ml.test.xxx.mapper.simple;

import java.util.List;

import com.ml.test.xxx.model.simple.NbReportData;
import com.ml.test.xxx.request.nb.NbReportDataRequest;

public interface NbReportDataMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NbReportData record);

    NbReportData selectByPrimaryKey(Long id);

    List<NbReportData> selectAll();

    List<NbReportData> selectByConditions(NbReportDataRequest req);

    int updateByPrimaryKey(NbReportData record);

}