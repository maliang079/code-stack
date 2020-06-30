package com.ml.test.xxx.controler;

import java.util.List;

import com.ml.test.base.response.ApiResponse;
import com.ml.test.xxx.model.complex.UserInClass;
import com.ml.test.xxx.model.simple.User;
import com.ml.test.xxx.service.UserService;
import com.ml.test.xxx.service.tas.TAUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mal on 2019/5/22.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TAUserService taUserService;

    @PostMapping("/insert")
    public ApiResponse insert(@RequestBody User user) {
        ApiResponse<Long> response = new ApiResponse<Long>();
//        userService.insert(user);
        taUserService.taTest(user);
        response.setPayload(user.getId());
        return response;
    }

    @GetMapping("/allUserInClass")
    public ApiResponse queryAllUserInClass() {
        ApiResponse<List<UserInClass>> response = new ApiResponse();
        User user = new User();
        user.setId(1L);
        response.setPayload(userService.queryAllUserInClass(user));
        return response;
    }

    @PostMapping("/textPlain")
    public ApiResponse textPlain(@RequestBody String text) {
        ApiResponse<String> response = new ApiResponse<String>();
        System.out.println(text);
        response.setPayload(text);
        return response;
    }

}
