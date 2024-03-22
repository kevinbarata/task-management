package com.project.resto.controller;

import com.project.resto.dto.SocialDto;
import com.project.resto.service.SocialService;
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
@RequestMapping(value="/social")
public class SocialController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SocialService socialService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto add(@RequestBody SocialDto socialDto) {
        logger.info("social.add parameter = " + socialDto);
        if (socialDto != null){
            int add = socialService.add(socialDto);
            return ResponseEntityBuilder.buildNormalResponse(add);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody SocialDto socialDto) {
        logger.info("social.getAll parameter = " + socialDto);
        if (socialDto != null){
            List<SocialDto> list = socialService.get(socialDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto update(@RequestBody SocialDto socialDto) {
        logger.info("social.update parameter = " + socialDto);
        if (socialDto != null){
            int update = socialService.update(socialDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }


}
