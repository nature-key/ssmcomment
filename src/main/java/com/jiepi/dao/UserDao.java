package com.jiepi.dao;

import com.jiepi.bean.User;

import java.util.List;

public interface UserDao {

    List<User> select(User user);

    int insert(User user);

    User selectById(Long id);

    int delete (Long id);

    int update(User user);
}
