
package com.ml.test.xxx.service.parsing;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * {hkwl+ack:I:2B0006963106758:127.0.0.1:5672:1b}
 * {hkwl+ack:C:2B0006963106758:07P100H1600L0600:bb}
 *
 */
public class AckParser extends AbstractParser<AckParseResult> {

    private static final String ACK_PREFIX = "hkwl+ack";

    private static final String CMD_TYPE_I = "I";

    private static final String CMD_TYPE_C = "C";

    @Override
    public AckParseResult parse(String data) {
        String dataTmp = strip(data);
        String[] array = StringUtils.split(dataTmp, SPLITTER);
        return AckParseResult.builder().cmdType(array[1]).imei(array[2]).build();
    }

    @Override
    public boolean isBelong(String data) {
        boolean isBelong = false;
        String dataTmp = strip(data);
        String[] array = StringUtils.split(dataTmp, SPLITTER);
        if (StringUtils.startsWith(dataTmp, ACK_PREFIX +  SPLITTER + CMD_TYPE_I) &&
                ArrayUtils.getLength(array) == 6 && validCheckSum(dataTmp)) {
            isBelong = true;
        } else if (StringUtils.startsWith(dataTmp, ACK_PREFIX +  SPLITTER + CMD_TYPE_C) &&
                ArrayUtils.getLength(array) == 5 && validCheckSum(dataTmp)) {
            isBelong = true;
        }
        return isBelong;
    }

}