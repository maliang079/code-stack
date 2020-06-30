package com.ml.test.xxx.service.parsing;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AckParseResult {

    private String cmdType;

    private String imei;

}