package com.jiepi.controller.system;

import com.jiepi.bean.User;
import com.jiepi.constant.PageCodeEnum;
import com.jiepi.dto.UserDto;
import com.jiepi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User add(@RequestBody UserDto userDto) {
        User user = userService.addUer(userDto);
        return user;
    }

    @RequestMapping(value = "/getUserlist", method = RequestMethod.GET)
    public List<UserDto> getList() {
        List<UserDto> list = userService.getList();
        return list;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public int update(@RequestBody UserDto userDto) {
        int modify = userService.modify(userDto);
        return modify;
    }
}
