package com.project.teacher.controller;

import com.project.teacher.config.JwtUtil;
import com.project.teacher.dao.UserSessionDao;
import com.project.teacher.dto.AttendanceDto;
import com.project.teacher.dto.BiodataDto;
import com.project.teacher.service.AttendanceService;
import com.project.teacher.service.TeacherBiodataService;
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
@RequestMapping(value="/biodata")
public class TeacherBiodataController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TeacherBiodataService teacherBiodataService;

    @Autowired
    private UserSessionDao userSessionDao;

    // buat atau perbaharui biodata guru
    @RequestMapping(value = "/addTeacherBiodata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto addTeacherBiodata(@RequestBody BiodataDto biodataDto) {
        logger.info("biodata.addTeacherBiodata parameter = " + biodataDto);
        if (biodataDto != null) {
            Long userId = biodataDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(biodataDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            // find biodata
            BiodataDto biodata = teacherBiodataService.getBiodataByTeacherId(biodataDto);
            if (biodata != null){
                int update = teacherBiodataService.update(biodataDto);
                if (update == 1) {
                    return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.UPDATE_BIODATA_SUCCESS.getMessage(), update);
                }
            }else {
                int insert = teacherBiodataService.insert(biodataDto);
                if (insert == 1) {
                    return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.UPDATE_BIODATA_SUCCESS.getMessage(), insert);
                }
            }

            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan biodata guru
    @RequestMapping(value = "/getBiodataByTeacherId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getBiodataByTeacherId(@RequestBody BiodataDto biodataDto) {
        logger.info("biodata.getBiodataByTeacherId parameter = " + biodataDto);
        if (biodataDto != null){
            Long userId = biodataDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(biodataDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            BiodataDto list = teacherBiodataService.getBiodataByTeacherId(biodataDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
