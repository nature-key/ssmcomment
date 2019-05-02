package com.jiepi.service.imp;

import com.jiepi.bean.Action;
import com.jiepi.bean.Menu;
import com.jiepi.dao.MenuDao;
import com.jiepi.dto.MenuDto;
import com.jiepi.dto.MenuForMoveDto;
import com.jiepi.dto.MenuForZtreeDto;
import com.jiepi.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<MenuDto> getList(MenuDto menuDto) {
        List<MenuDto> result = new ArrayList<>();
        Menu menuForSelect = new Menu();
        BeanUtils.copyProperties(menuDto, menuForSelect);
        List<Menu> menuList = menuDao.select(menuForSelect);
        for (Menu menu : menuList) {
            MenuDto menuDtoTemp = new MenuDto();
            result.add(menuDtoTemp);
            BeanUtils.copyProperties(menu, menuDtoTemp);
        }
        return result;
    }

    @Override
    public boolean add(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);
        return menuDao.insert(menu) == 1;
    }

    @Override
    public boolean remove(Long id) {
        return menuDao.delete(id) == 1;
    }

    @Override
    public MenuDto getById(Long id) {
        MenuDto result = new MenuDto();
//        Menu menu = menuDao.selectById(id);
//        BeanUtils.copyProperties(menu, result);
        return result;
    }

    @Override
    public boolean modify(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);
        return menuDao.update(menu) == 1;
    }

    @Override
    public int order(MenuForMoveDto menuForMoveDto) {
        if (menuForMoveDto.getMoveType().equals(MenuForMoveDto.MOVE_TYPE_INNER)) {
            menuDao.updateOrderNumByParentId(menuForMoveDto.getTargetNodeId());

            Menu menu = new Menu();
            menu.setOrderNum(1);
            menu.setParentId(menuForMoveDto.getTargetNodeId());
            menu.setId(menuForMoveDto.getDropNodeId());
            menuDao.update(menu);
        } else {
            Menu menu = menuDao.selectById(menuForMoveDto.getTargetNodeId());
            if (menu != null) {
                if (MenuForMoveDto.MOVE_TYPE_PREV.equals(menuForMoveDto.getMoveType())) {
                    int i = menuDao.updateOrderNumByIdInclude(menuForMoveDto.getTargetNodeId());
                    Menu menu1 = new Menu();
                    menu1.setParentId(menu.getParentId());
                    menu1.setOrderNum(menu.getOrderNum());
                    menu1.setId(menuForMoveDto.getDropNodeId());
                    menuDao.update(menu1);
                } else if (MenuForMoveDto.MOVE_TYPE_NEXT.equals(menuForMoveDto.getMoveType())) {
                    int i = menuDao.updateOrderNumById(menuForMoveDto.getTargetNodeId());
                    Menu menu1 = new Menu();
                    menu1.setId(menuForMoveDto.getDropNodeId());
                    menu1.setOrderNum(menu.getOrderNum() + 1);
                    menu1.setParentId(menu.getParentId());
                    menuDao.update(menu1);
                } else {

                    return 0;
                }
            } else {
                return 0;
            }

        }
        return 1;
    }


    @Override
    public List<MenuForZtreeDto> getZtreeList() {
        List<MenuForZtreeDto> result = new ArrayList<>();
        List<Menu> menus = menuDao.selectWithAction(new Menu());
        for (Menu menu : menus) {
            MenuForZtreeDto menuForZtreeDto= new MenuForZtreeDto();
            result.add(menuForZtreeDto);
            BeanUtils.copyProperties(menu,menuForZtreeDto);
            menuForZtreeDto.setOpen(true);
            menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_MENU+menu.getId());
            menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU+menu.getParentId());
            for (Action action:menu.getActionList()
                 ) {
                menuForZtreeDto = new MenuForZtreeDto();
                result.add(menuForZtreeDto);
                menuForZtreeDto.setComboId(MenuForZtreeDto.PREFIX_ACTION + action.getId());
                menuForZtreeDto.setComboParentId(MenuForZtreeDto.PREFIX_MENU + action.getMenuId());
                menuForZtreeDto.setName(action.getName());
                menuForZtreeDto.setId(action.getId());
                menuForZtreeDto.setParentId(action.getMenuId());
            }
        }
        return result;
    }
}
