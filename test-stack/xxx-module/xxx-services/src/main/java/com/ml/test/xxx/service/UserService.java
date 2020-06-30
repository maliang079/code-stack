package com.ml.test.xxx.service;

import java.util.List;
import java.util.Map;

import com.ml.test.base.exception.Exceptions;
import com.ml.test.xxx.mapper.complex.UserComplexMapper;
import com.ml.test.xxx.mapper.simple.UserMapper;
import com.ml.test.xxx.model.complex.UserInClass;
import com.ml.test.xxx.model.simple.CategoryDictionary;
import com.ml.test.xxx.model.simple.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mal on 2019/5/22.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserComplexMapper userComplexMapper;

    @Transactional
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Transactional
    public int update(User user) {
        int cnt = userMapper.update(user);
        if (cnt == 1) {
            Exceptions.rollback("更新成功，故意抛出回滚异常");
        }
        return cnt;
    }

    public List<UserInClass> queryAllUserInClass(User user) {

        Map<String, CategoryDictionary> categorys = userComplexMapper.queryByCategory();

        return userComplexMapper.queryAllUserInClass(user);
    }

}
