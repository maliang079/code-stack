package com.ml.test.xxx.service.parsing;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mal on 2019/7/5.
 */
public class SensorParser extends AbstractParser<ParseResult> {

    @Override
    public boolean isBelong(String data) {
        String dataTmp = strip(data);
        String[] array = StringUtils.split(dataTmp, SPLITTER);
        return (ArrayUtils.getLength(array) == 4) && validCheckSum(dataTmp);
    }

    @Override
    public ParseResult parse(String data) {
        ParseResult pr = null;
        String dataTmp = strip(data);
        if (validCheckSum(dataTmp)) {
            pr = new ParseResult();
            pr.setOriginData(data);
            pr.setDevices(parseType(dataTmp));
            pr.setImei(parseImei(dataTmp));
            String[] arrays = StringUtils.split(dataTmp, SPLITTER);
            pr.setVoltage(parseVoltage(arrays[2]));
            parseSigns(arrays[3], pr);
            pr.setPressure(pressure(arrays[1], pr.getDevices()));
        }

        return pr;
    }

    protected String pressure(String data, Devices devices) {
        Units units = Units.fromDevices(devices);
        String strPressure = StringUtils.replaceChars(data, "KkPpAa", StringUtils.EMPTY);
        if (StringUtils.equalsIgnoreCase(strPressure, "----"))
            return data;
        double pressure = Double.valueOf(strPressure) / units.getFactors();
        return StringUtils.joinWith(StringUtils.EMPTY, String.valueOf(pressure), units.getUnit());

    }

    protected Devices parseType(String data) {
        return Devices.fromStr(StringUtils.substring(data, 0, 1));
    }

    protected String parseImei(String data) {
        return StringUtils.substring(StringUtils.substringBefore(data, SPLITTER), 1);
    }

    protected String parseVoltage(String data) {
        /*double voltage = Double.valueOf(StringUtils.replaceChars(data, "Vv", StringUtils.EMPTY));
        return (voltage < 3.3d) ? StringUtils.joinWith(" ", data, "电池欠压") : data;*/
        return data;
    }

    protected void parseSigns(String data, ParseResult pr) {
        int start = 0;
        int end = 1;
        int bitOne = Integer.valueOf(StringUtils.substring(data, start++, end++));
        pr.setBitOne(Signs.BitOne.fromInt(bitOne));
        int bitTow = Integer.valueOf(StringUtils.substring(data, start++, end++));
        pr.setBitTow(Signs.BitTow.fromInt(bitTow));
        int bitThree = Integer.valueOf(StringUtils.substring(data, start++, end++));
        pr.setBitThree(Signs.BitThree.fromInt(bitThree));
        int bitFour = Integer.valueOf(StringUtils.substring(data, start++, end++));
        pr.setBitFour(Signs.BitFour.fromInt(bitFour));
    }

}
