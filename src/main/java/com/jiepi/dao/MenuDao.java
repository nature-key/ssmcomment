package com.jiepi.dao;

import com.jiepi.bean.Menu;

import java.util.List;

public interface MenuDao {

    /**
     * 修改
     *
     * @param menu
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 根据查询条件查询菜单列表（仅本表单表查询）
     *
     * @param menu 查询条件
     * @return 菜单列表
     */
    List<Menu> select(Menu menu);

    /**
     * 新增
     *
     * @param menu
     * @return 影响行数
     */
    int insert(Menu menu);

    int updateOrderNumByParentId(Long id);

    int updateOrderNumByIdInclude(Long id);

    int updateOrderNumById(Long id);

    Menu selectById(Long id);
}
