package com.ml.test.xxx.controler;

import com.ml.test.base.response.ApiResponse;
import com.ml.test.xxx.service.NbDrivenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mal on 2019/7/8.
 */
@RestController
@RequestMapping("/nb")
public class NbDrivenController {

    @Autowired
    private NbDrivenService nbDrivenService;

    @PostMapping("/receive")
    public ApiResponse receive(@RequestBody String data) {
        ApiResponse<String> response = new ApiResponse<String>();
        nbDrivenService.receive(data);
        return response;
    }

}
