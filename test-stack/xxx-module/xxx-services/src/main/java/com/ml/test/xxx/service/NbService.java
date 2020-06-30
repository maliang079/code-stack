package com.ml.test.xxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ml.test.xxx.mapper.complex.NbMapper;
import com.ml.test.xxx.mapper.simple.NbReportDataMapper;
import com.ml.test.xxx.model.complex.NbAlarmDetail;
import com.ml.test.xxx.model.simple.NbReportData;
import com.ml.test.xxx.request.nb.NbAlarmDetailRequest;
import com.ml.test.xxx.request.nb.NbReportDataRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mal on 2019/7/9.
 */
@Service
public class NbService {

    @Autowired
    private NbReportDataMapper nbReportDataMapper;

    @Autowired
    private NbMapper nbMapper;

    public PageInfo<NbReportData> select(NbReportDataRequest req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        if (StringUtils.isBlank(req.getOrderBy()))
            req.setOrderBy("create_time desc");
        return new PageInfo<NbReportData>(nbReportDataMapper.selectByConditions(req));
    }

    public PageInfo<NbAlarmDetail> selectAlarmDetail(NbAlarmDetailRequest req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        if (StringUtils.isBlank(req.getOrderBy()))
            req.setOrderBy("alarm_start_time desc");
        return new PageInfo<NbAlarmDetail>(nbMapper.selectNbAlarmDetail(req));
    }

}
