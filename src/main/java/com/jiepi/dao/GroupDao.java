package com.jiepi.dao;

import com.jiepi.bean.Group;

import java.util.List;

public interface GroupDao {

    int delete(Long id);

    List<Group> select (Group group);

    int insert(Group group);

    Group selectById(Long id);

    int update(Group group);

    Group selectMenuListById(Long id);

    Group selectMenuByGroupId(Long id);
}
