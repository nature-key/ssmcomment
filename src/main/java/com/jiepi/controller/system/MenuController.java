package com.jiepi.controller.system;

import com.jiepi.constant.PageCodeEnum;
import com.jiepi.dto.MenuDto;
import com.jiepi.dto.MenuForMoveDto;
import com.jiepi.dto.MenuForZtreeDto;
import com.jiepi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 新增菜单
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean add(@RequestBody MenuDto menuDto) {
        boolean add = menuService.add(menuDto);
        return add;
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public int move(@RequestBody MenuForMoveDto menuForMoveDto) {
        int order = menuService.order(menuForMoveDto);
        return order;

    }

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public List<MenuForZtreeDto> select() {
        List<MenuForZtreeDto> ztreeList = menuService.getZtreeList();
        return ztreeList;
    }
}
