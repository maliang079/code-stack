package com.ml.test.xxx.mapper.simple;

import java.util.List;

import com.ml.test.xxx.model.simple.User;

/**
 * Created by mal on 2019/5/22.
 */
public interface UserMapper {

    List<User> findByCondition(User user);

    User getById(Long id);

    int getCountByCondition(User user);

    int insert(User user);

    int update(User user);

    int deleteByIds(Long[] id);

    int deleteById(Long id);

}
