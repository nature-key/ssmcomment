package com.jiepi.dto;

import com.jiepi.bean.Group;
import com.jiepi.bean.Menu;

import java.util.List;

public class GroupDto extends Group {

    private Integer pId;

    private List<Long> menuId;

    public List<Long> getMenuId() {
        return menuId;
    }

    public void setMenuId(List<Long> menuId) {
        this.menuId = menuId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
