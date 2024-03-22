package com.project.resto.controller;

import com.project.resto.dto.MenuCategoryDto;
import com.project.resto.dto.MenuDto;
import com.project.resto.service.MenuService;
import com.project.resto.util.ErrorCodeEnum;
import com.project.resto.util.ResponseEntityBuilder;
import com.project.resto.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/menu")
public class MenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuService menuService;

    //menu
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto add(@RequestBody MenuDto menuDto) {
        logger.info("menu.add parameter = " + menuDto);
        if (menuDto != null){
            int add = menuService.add(menuDto);
            return ResponseEntityBuilder.buildNormalResponse(add);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody MenuDto menuDto) {
        logger.info("menu.getAll parameter = " + menuDto);
        if (menuDto != null){
            List<MenuDto> list = menuService.get(menuDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto update(@RequestBody MenuDto menuDto) {
        logger.info("menu.update parameter = " + menuDto);
        if (menuDto != null){
            int update = menuService.update(menuDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    //category

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto addCategory(@RequestBody MenuCategoryDto menuCategoryDto) {
        logger.info("menu.add addCategory = " + menuCategoryDto);
        if (menuCategoryDto != null){
            int add = menuService.addCategory(menuCategoryDto);
            return ResponseEntityBuilder.buildNormalResponse(add);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/getCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getCategory(@RequestBody MenuCategoryDto menuCategoryDto) {
        logger.info("menu.getCategory parameter = " + menuCategoryDto);
        if (menuCategoryDto != null){
            List<MenuCategoryDto> list = menuService.getCategory(menuCategoryDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto updateCategory(@RequestBody MenuCategoryDto menuCategoryDto) {
        logger.info("menu.updateCategory parameter = " + menuCategoryDto);
        if (menuCategoryDto != null){
            int update = menuService.updateCategory(menuCategoryDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }
}
