package com.jiepi.service;

import com.jiepi.dto.MenuDto;
import com.jiepi.dto.MenuForMoveDto;

import java.util.List;

public interface MenuService {

    /**
     * 根据条件获取菜单列表
     * @param menuDto 过滤条件
     * @return
     */
    List<MenuDto> getList(MenuDto menuDto);

    /**
     * 新增菜单
     * @param menuDto
     * @return true:新增成功;false:新增失败
     */
    boolean add(MenuDto menuDto);

    /**
     * 删除菜单
     * @param id
     * @return true:删除成功;false:删除失败
     */
    boolean remove(Long id);

    MenuDto getById(Long id);

    /**
     * 修改菜单
     * @param menuDto
     * @return true:修改成功;false:修改失败
     */
    boolean modify(MenuDto menuDto);

    /**
     * 排序
     * @param menuForMoveDto
     * @return
     */
    int order(MenuForMoveDto menuForMoveDto);
}
