package com.ml.test.xxx.service;

import java.sql.Timestamp;

import com.ml.test.xxx.mapper.simple.NbAlarmDataMapper;
import com.ml.test.xxx.mapper.simple.NbReportDataMapper;
import com.ml.test.xxx.model.simple.NbAlarmData;
import com.ml.test.xxx.model.simple.NbReportData;
import com.ml.test.xxx.service.parsing.AckParser;
import com.ml.test.xxx.service.parsing.AckParseResult;
import com.ml.test.xxx.service.parsing.IParser;
import com.ml.test.xxx.service.parsing.ParseResult;
import com.ml.test.xxx.service.parsing.SensorParser;
import com.ml.test.xxx.service.parsing.Signs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mal on 2019/7/8.
 */
@Service
public class NbDrivenService {

    private Logger logger = LoggerFactory.getLogger(NbDrivenService.class);

    @Autowired
    private NbReportDataMapper nbReportDataMapper;

    @Autowired
    private NbAlarmDataMapper nbAlarmDataMapper;

    public void receive(String data) {
        logger.info("origin data: {}", data);
        IParser<ParseResult> sensorParser = new SensorParser();
        IParser<AckParseResult> ackParser = new AckParser();
        if (sensorParser.isBelong(data)) {
            ParseResult pr = sensorParser.parse(data);
            if (pr != null) {
                NbReportData rd = obtainReportData(pr);
                nbReportDataMapper.insert(rd);
                if (!isNormal(pr)) {
                    nbAlarmDataMapper.insert(obtainAlarmData(rd));
                }
            }
        } else if (ackParser.isBelong(data)) {
            AckParseResult pr = ackParser.parse(data);
            logger.info("cmd type:{}, imei: {}", pr.getCmdType(), pr.getImei());
        } else {
            logger.warn("not matcher parser. origin data: {}", data);
        }
    }

    private boolean isNormal(ParseResult pr) {
        return pr.getBitOne() == Signs.BitOne.NORMAL &&
                pr.getBitTow() == Signs.BitTow.NORMAL &&
                pr.getBitThree() == Signs.BitThree.NORMAL &&
                pr.getBitFour() == Signs.BitFour.NORMAL;
    }

    private NbAlarmData obtainAlarmData(NbReportData rd) {
        NbAlarmData ad = new NbAlarmData();
        ad.setNbRdId(rd.getId());
        Timestamp current = new Timestamp(System.currentTimeMillis());
        ad.setAlarmStartTime(current);
        ad.setCreateTime(current);
        return ad;
    }

    private NbReportData obtainReportData(ParseResult pr) {
        NbReportData rd = new NbReportData();
        rd.setType(pr.getDevices().getCode());
        rd.setName(pr.getDevices().getName());
        rd.setImei(pr.getImei());
        rd.setPressure(pr.getPressure());
        rd.setVoltage(pr.getVoltage());
        rd.setOriginData(pr.getOriginData());
        rd.setSignBitOne(pr.getBitOne().getCode());
        rd.setSignBitOneRemark(pr.getBitOne().getRemark());
        rd.setSignBitTow(pr.getBitTow().getCode());
        rd.setSignBitTowRemark(pr.getBitTow().getRemark());
        rd.setSignBitThree(pr.getBitThree().getCode());
        rd.setSignBitThreeRemark(pr.getBitThree().getRemark());
        rd.setSignBitFour(pr.getBitFour().getCode());
        rd.setSignBitFourRemark(pr.getBitFour().getRemark());
        Timestamp current = new Timestamp(System.currentTimeMillis());
        rd.setCreateTime(current);
        return rd;
    }

}
