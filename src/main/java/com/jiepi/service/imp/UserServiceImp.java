package com.jiepi.service.imp;

import com.jiepi.bean.User;
import com.jiepi.dao.UserDao;
import com.jiepi.dto.UserDto;
import com.jiepi.service.UserService;
import com.jiepi.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserDto> getList() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = userDao.select(new User());
        userList.forEach(it -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(it, userDto);
            userDtoList.add(userDto);
        });
        return userDtoList;
    }

    @Override
    public User addUer(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(MD5Util.getMD5(userDto.getPassword()));
        int inert = userDao.insert(user);
        return user;


    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int modify(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        int update = userDao.update(user);

        return update;
    }
}
