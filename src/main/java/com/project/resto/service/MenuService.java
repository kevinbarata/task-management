package com.project.resto.service;

import com.project.resto.dto.AssetDto;
import com.project.resto.dto.MenuCategoryDto;
import com.project.resto.dto.MenuDto;

import java.util.List;

public interface MenuService {

    //menu
    int add(MenuDto menuDto);

    List<MenuDto> get(MenuDto menuDto);

    int update(MenuDto menuDto);

    //category

    int addCategory(MenuCategoryDto menuCategoryDto);
    List<MenuCategoryDto> getCategory(MenuCategoryDto menuCategoryDto);

    int updateCategory(MenuCategoryDto menuCategoryDto);
}
