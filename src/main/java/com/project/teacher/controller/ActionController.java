package com.project.teacher.controller;

import com.project.teacher.config.JwtUtil;
import com.project.teacher.dao.UserSessionDao;
import com.project.teacher.dto.ELearningDto;
import com.project.teacher.dto.EssayDto;
import com.project.teacher.service.StudyService;
import com.project.teacher.util.ErrorCodeEnum;
import com.project.teacher.util.ResponseEntityBuilder;
import com.project.teacher.util.ResponseEntityDto;
import com.project.teacher.util.SuccessCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/action")
public class ActionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudyService studyService;

    @Autowired
    private UserSessionDao userSessionDao;

    //buat atau perbaharui materi
    @RequestMapping(value = "/createELearningMaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto createELearningMaterial(@RequestBody ELearningDto dto) {
        logger.info("action.createELearningMaterial parameter = " + dto);
        if (dto != null) {
            Long userId = dto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto insert = studyService.createELearningMaterial(dto);
            if (insert.getStatus().equals("1")) {
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CREATE_ELEARNING_SUCCESS.getMessage(), insert.getData());
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan semua list materi
    @RequestMapping(value = "/getAllMaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllMaterial(@RequestBody ELearningDto dto) {
        logger.info("action.getAllMaterial parameter = " + dto);
        if (dto != null){
            Long userId = dto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
           ResponseEntityDto list = studyService.getAllMaterial(dto);
            return ResponseEntityBuilder.buildNormalResponse(list.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/createNewEssay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto createNewEssay(@RequestBody EssayDto dto) {
        logger.info("action.createNewEssay parameter = " + dto);
        if (dto != null) {
            Long userId = dto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto insert = studyService.createNewEssay(dto);
            if (insert.getStatus().equals("1")) {
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CREATE_ELEARNING_SUCCESS.getMessage(), insert.getData());
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/getAllEssay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllEssay(@RequestBody EssayDto dto) {
        logger.info("action.getAllEssay parameter = " + dto);
        if (dto != null){
            Long userId = dto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto list = studyService.getAllEssay(dto);
            return ResponseEntityBuilder.buildNormalResponse(list.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/markEssayAsCompleted", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto markEssayAsCompleted(@RequestBody EssayDto dto) {
        logger.info("action.markEssayAsCompleted parameter = " + dto);
        if (dto != null){
            Long userId = dto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto update = studyService.markEssayAsCompleted(dto);
            if (update.getStatus().equals("1")){
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.TASK_COMPLETED.getMessage(),update.getData());
            }
            return ResponseEntityBuilder.buildNormalResponse();
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
