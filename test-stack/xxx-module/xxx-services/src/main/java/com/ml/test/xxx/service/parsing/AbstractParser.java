package com.ml.test.xxx.service.parsing;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractParser<T> implements IParser<T> {

    private Logger logger = LoggerFactory.getLogger(AbstractParser.class);

    protected String strip(String data) {
        String dataTmp = StringUtils.replaceChars(data, STARTER, StringUtils.EMPTY);
        dataTmp = StringUtils.replaceChars(dataTmp, TERMINATOR, StringUtils.EMPTY);
        return dataTmp;
    }

    protected boolean validCheckSum(String data) {
        int end = StringUtils.length(data) - 2;
        String checkSum = StringUtils.substring(data, end);
        data = StringUtils.substring(data, 0, end);

        int sum = 0;
        for (char c : data.toCharArray()) {
            sum += c;
        }
        String strHexSum = Integer.toHexString(sum);
        end = StringUtils.length(strHexSum) - 2;
        String checkSumWk = StringUtils.substring(strHexSum, end);

        logger.info("origin checksum:{}, compute checksum: {}", checkSum, checkSumWk);
        return StringUtils.equalsIgnoreCase(checkSum, checkSumWk);
    }

}