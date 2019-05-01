package com.jiepi.service;

import com.jiepi.bean.User;
import com.jiepi.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getList();

    User addUer(UserDto user);

    int delete(Long id);

    int modify(UserDto userDto);
}
