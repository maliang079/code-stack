package com.ml.test.xxx.service.tas;

import com.ml.test.xxx.model.simple.User;
import com.ml.test.xxx.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mal on 2019/6/6.
 */
@Service
public class TAUserService {

    @Autowired
    private UserService userService;

    @Transactional
    public void taTest(User user) {
        userService.insert(user);
        userService.update(user);
    }

}
