package com.jiepi.controller.system;

import com.jiepi.dto.GroupDto;
import com.jiepi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public int add(@RequestBody GroupDto groupDto) {
        int add = groupService.add(groupDto);
        return add;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<GroupDto> getList() {
        List<GroupDto> list = groupService.getList();
        return list;

    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public int remove(@PathVariable Long id) {
        int remove = groupService.remove(id);
        return remove;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public GroupDto selectById(@RequestParam Long id) {
        GroupDto groupDto = groupService.selectById(id);
        return groupDto;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Boolean update(@RequestBody GroupDto groupDto) {
        boolean modify = groupService.modify(groupDto);
        return modify;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public int insertBach(@RequestBody GroupDto groupDto) {
        int i = groupService.assignMenu(groupDto);
        return i;
    }

}
