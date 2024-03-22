package com.project.resto.controller;

import com.project.resto.dto.AssetDto;
import com.project.resto.service.AssetService;
import com.project.resto.util.ErrorCodeEnum;
import com.project.resto.util.ResponseEntityBuilder;
import com.project.resto.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/asset")
public class AssetController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AssetService assetService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto add(@RequestBody AssetDto assetDto) {
        logger.info("asset.add parameter = " + assetDto);
        if (assetDto != null){
            int add = assetService.add(assetDto);
            return ResponseEntityBuilder.buildNormalResponse(add);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody AssetDto assetDto) {
        logger.info("asset.getAll parameter = " + assetDto);
        if (assetDto != null){
            List<AssetDto> list = assetService.get(assetDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto update(@RequestBody AssetDto assetDto) {
        logger.info("asset.update parameter = " + assetDto);
        if (assetDto != null){
            int update = assetService.update(assetDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
