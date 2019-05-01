package com.jiepi.service;

import com.jiepi.bean.Group;
import com.jiepi.dao.GroupDao;
import com.jiepi.dto.GroupDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> getList();

    boolean modify(GroupDto groupDto);

    int add (GroupDto groupDto);

    GroupDto selectById(Long id);

    int remove(Long Id);

    int assignMenu(GroupDto groupDto);


}
