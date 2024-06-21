package com.project.teacher.controller;

import com.project.teacher.config.JwtUtil;
import com.project.teacher.dao.UserSessionDao;
import com.project.teacher.dto.AttendanceDto;
import com.project.teacher.service.AttendanceService;
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
@RequestMapping(value="/attendance")
public class AttendanceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserSessionDao userSessionDao;

    // absen masuk
    @RequestMapping(value = "/checkIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto checkIn(@RequestBody AttendanceDto attendanceDto) {
        logger.info("attendance.checkIn parameter = " + attendanceDto);
        if (attendanceDto != null) {
            Long userId = attendanceDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(attendanceDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (attendanceService.getTodayLog(attendanceDto)!= null){
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.CHECK_IN_DONE_TODAY);
            }
            int add = attendanceService.checkIn(attendanceDto);
            if (add == 1) {
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CHECK_IN_SUCCESS.getMessage(), add);
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // absen keluar
    @RequestMapping(value = "/checkOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto checkOut(@RequestBody AttendanceDto attendanceDto) {
        logger.info("attendance.checkOut parameter = " + attendanceDto);
        if (attendanceDto != null) {
            Long userId = attendanceDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(attendanceDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            // cek absen
            AttendanceDto checkLogToday = attendanceService.getTodayLog(attendanceDto);
            if (checkLogToday.getId() == null){
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.CHECK_IN_UN_DONE_TODAY);
            } else {
                attendanceDto.setId(checkLogToday.getId());
                attendanceDto.setCheckInTime(checkLogToday.getCheckInTime());
                int add = attendanceService.checkOut(attendanceDto);
                if (add == 1) {
                    return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CHECK_OUT_SUCCESS.getMessage(), add);
                }
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    // mendapatkan absensi guru
    @RequestMapping(value = "/getAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAll(@RequestBody AttendanceDto attendanceDto) {
        logger.info("attendance.getAll parameter = " + attendanceDto);
        if (attendanceDto != null){
            Long userId = attendanceDto.getTeacherId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(attendanceDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            List<AttendanceDto> list = attendanceService.getAll(attendanceDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
