package com.ml.test.xxx.controler;

import com.github.pagehelper.PageInfo;
import com.ml.test.base.response.ApiResponse;
import com.ml.test.xxx.request.nb.NbAlarmDetailRequest;
import com.ml.test.xxx.request.nb.NbReportDataRequest;
import com.ml.test.xxx.service.NbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mal on 2019/7/9.
 */
@RestController
@RequestMapping("/nb")
public class NbController {

    @Autowired
    private NbService nbService;

    @PostMapping("/data")
    public @ResponseBody ApiResponse<PageInfo> selectReportData(@RequestBody NbReportDataRequest req) {
        /* req eg:
        {
            "imei":"2B0006963106758",
            "startTime":"2019-07-08 19:08:31",
            "endTime":"2019-07-08 19:08:31",
            "orderBy":"create_time desc",
            "pageNum":1,
            "pageSize":10
        }
        */
        ApiResponse<PageInfo> response = new ApiResponse<PageInfo>();
        response.setPayload(nbService.select(req));
        return response;
    }

    @PostMapping("/alarm")
    public @ResponseBody ApiResponse<PageInfo> selectAlarmDetail(@RequestBody NbAlarmDetailRequest req) {
        ApiResponse<PageInfo> response = new ApiResponse<PageInfo>();
        response.setPayload(nbService.selectAlarmDetail(req));
        return response;
    }

}
