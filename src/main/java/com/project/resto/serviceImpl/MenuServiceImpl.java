package com.project.resto.serviceImpl;

import com.project.resto.dao.AssetDao;
import com.project.resto.dao.MenuDao;
import com.project.resto.dto.AssetDto;
import com.project.resto.dto.MenuCategoryDto;
import com.project.resto.dto.MenuDto;
import com.project.resto.service.AssetService;
import com.project.resto.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    //menu
    @Override
    public int add(MenuDto menuDto) {
        menuDto.setStatus(1);
        int add = menuDao.add(menuDto);
        return add;
    }

    @Override
    public List<MenuDto> get (MenuDto menuDto) {
        List<MenuDto> list = menuDao.get(menuDto);
        return list;
    }

    @Override
    public int update(MenuDto menuDto) {
        int update = menuDao.update(menuDto);
        return update;
    }

    //category

    @Override
    public int addCategory(MenuCategoryDto menuCategoryDto) {
        menuCategoryDto.setStatus(1);
        int add = menuDao.addCategory(menuCategoryDto);
        return add;
    }

    @Override
    public List<MenuCategoryDto> getCategory (MenuCategoryDto menuCategoryDto) {
        List<MenuCategoryDto> list = menuDao.getCategory(menuCategoryDto);
        return list;
    }

    @Override
    public int updateCategory(MenuCategoryDto menuCategoryDto) {
        int update = menuDao.updateCategory(menuCategoryDto);
        return update;
    }
}
