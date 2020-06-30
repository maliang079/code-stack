package com.ml.test.xxx.request.nb;

import lombok.Data;

/**
 * Created by mal on 2019/7/9.
 */
@Data
public class NbReportDataRequest extends NbBaseRequest{

    private String imei;

    private String startTime;

    private String endTime;

}
