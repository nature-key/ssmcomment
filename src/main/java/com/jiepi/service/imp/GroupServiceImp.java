package com.jiepi.service.imp;

import com.jiepi.bean.Group;
import com.jiepi.bean.GroupMenu;
import com.jiepi.dao.GroupDao;
import com.jiepi.dao.GroupMenuDao;
import com.jiepi.dto.GroupDto;
import com.jiepi.service.GroupService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupMenuDao groupMenuDao;

    @Override
    public List<GroupDto> getList() {
        List<GroupDto> groupDtoList = new ArrayList<>();
        List<Group> groupList = groupDao.select(new Group());
        groupList.forEach(it -> {
            GroupDto groupDto = new GroupDto();
            groupDtoList.add(groupDto);
            BeanUtils.copyProperties(it, groupDto);
        });
        return groupDtoList;
    }

    @Override
    public boolean modify(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto, group);
        int update = groupDao.update(group);
        if (update > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int add(GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto, group);
        System.out.println(group.toString());
        int insert = groupDao.insert(group);
        return insert;
    }

    @Override
    public GroupDto selectById(Long id) {
        GroupDto groupDto = new GroupDto();
        Group group = groupDao.selectById(id);
        BeanUtils.copyProperties(group, groupDto);
        return groupDto;
    }

    @Override
    public int remove(Long Id) {
        int delete = groupDao.delete(Id);
        return delete;
    }

    @Override
    public int assignMenu(GroupDto groupDto) {
        ///todo 删除之前的组合菜单的关系重新分配关系

        if (groupDto.getMenuId() != null && groupDto.getMenuId().size() > 0) {
            List<GroupMenu> list = new ArrayList<>();
            List<Long> menuId = groupDto.getMenuId();
            menuId.forEach(it -> {
                GroupMenu groupMenu = new GroupMenu();
                groupMenu.setGroupId(groupDto.getId());
                groupMenu.setMenuId(it);
                list.add(groupMenu);
            });
            int insert = groupMenuDao.insert(list);
            return insert;
        }
        return 0;
    }

    @Override
    public GroupDto selectMenuByGroupId(Long id) {
        GroupDto groupDto = new GroupDto();
        Group group = groupDao.selectMenuByGroupId(id);
        BeanUtils.copyProperties(group, groupDto);
        return groupDto;
    }
}
