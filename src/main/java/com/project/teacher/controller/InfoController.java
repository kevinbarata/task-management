package com.project.teacher.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.project.teacher.config.JwtUtil;
import com.project.teacher.dao.UserSessionDao;
import com.project.teacher.dto.BiodataDto;
import com.project.teacher.dto.StudentAttendanceDto;
import com.project.teacher.dto.StudentDto;
import com.project.teacher.service.StudentService;
import com.project.teacher.service.TeacherBiodataService;
import com.project.teacher.util.ErrorCodeEnum;
import com.project.teacher.util.ResponseEntityBuilder;
import com.project.teacher.util.ResponseEntityDto;
import com.project.teacher.util.SuccessCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value="/info")
public class InfoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserSessionDao userSessionDao;

    // mendapatkan list semua murid
    @RequestMapping(value = "/getAllStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllStudent(@RequestBody StudentDto studentDto) {
        logger.info("info.getAllStudent parameter = " + studentDto);
        if (studentDto != null){
            Long userId = studentDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(studentDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto res = studentService.getAllStudent(studentDto);
            return ResponseEntityBuilder.buildNormalResponse(res.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan biodata murid
    @RequestMapping(value = "/getBiodataByStudentId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getBiodataByStudentId(@RequestBody BiodataDto biodataDto) {
        logger.info("info.getBiodataByStudentId parameter = " + biodataDto);
        if (biodataDto != null){
            Long userId = biodataDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(biodataDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto res = studentService.getBiodataByStudentId(biodataDto);
            return ResponseEntityBuilder.buildNormalResponse(res.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan data kehadiran murid
    @RequestMapping(value = "/getStudentAttendance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getStudentAttendance(@RequestBody StudentAttendanceDto dto) {
        logger.info("info.getStudentAttendance parameter = " + dto);
        if (dto != null){
            Long userId = dto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(dto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            ResponseEntityDto res = studentService.getStudentAttendance(dto);
            return ResponseEntityBuilder.buildNormalResponse(res.getData());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
