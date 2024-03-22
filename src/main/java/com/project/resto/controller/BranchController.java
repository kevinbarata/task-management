package com.project.resto.controller;

import com.project.resto.dto.BranchDto;
import com.project.resto.service.BranchService;
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
@RequestMapping(value="/branch")
public class BranchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BranchService branchService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto add(@RequestBody BranchDto branchDto) {
        logger.info("branch.add parameter = " + branchDto);
        if (branchDto != null){
            int add = branchService.add(branchDto);
            return ResponseEntityBuilder.buildNormalResponse(add);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody BranchDto branchDto) {
        logger.info("branch.getAll parameter = " + branchDto);
        if (branchDto != null){
            List<BranchDto> list = branchService.get(branchDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto update(@RequestBody BranchDto branchDto) {
        logger.info("branch.update parameter = " + branchDto);
        if (branchDto != null){
            int update = branchService.update(branchDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }


}
