package com.project.resto.dao;

import com.project.resto.dto.MenuCategoryDto;
import com.project.resto.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao {

    //menu
    int add(MenuDto menuDto);

    List<MenuDto> get(MenuDto menuDto);

    int update(MenuDto menuDto);

    //category

    int addCategory(MenuCategoryDto menuCategoryDto);
    List<MenuCategoryDto> getCategory(MenuCategoryDto menuCategoryDto);

    int updateCategory(MenuCategoryDto menuCategoryDto);
}
