package com.project.resto.controller;

import com.project.resto.dto.*;
import com.project.resto.service.AdminService;
import com.project.resto.service.AssetService;
import com.project.resto.service.AuthService;
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
@RequestMapping(value="/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto register(@RequestBody AdminDto adminDto) {
        logger.info("admin.register parameter = " + adminDto);
        if (adminDto != null){
            //find code by email
            AuthDto authDto = new AuthDto();
            authDto.setEmail(adminDto.getEmail());
            authDto.setCode(adminDto.getCode());
            authDto.setType("Register");
            AuthDto authDto1 = authService.findCodeByEmail(authDto);
            if (authDto1 != null) {
                //otp already used
                AuthDto authDto2 = new AuthDto();
                authDto2.setId(authDto1.getId());
                authDto2.setStatus(2);
                authService.update(authDto2);
                ResponseEntityDto register = adminService.register(adminDto);
                return register;
            } else {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.AUTH_WRONG);
            }
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto login(@RequestBody AdminDto adminDto) {
        logger.info("admin.login parameter = " + adminDto);
        if (adminDto != null){
            ResponseEntityDto login = adminService.login(adminDto);
            return login;
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto logout(@RequestBody AdminSessionDto adminSessionDto) {
        logger.info("admin.logout parameter = " + adminSessionDto);
        if (adminSessionDto != null){
            ResponseEntityDto logout = adminService.logout(adminSessionDto);
            return logout;
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        logger.info("admin.changePassword parameter = " + changePasswordDto);
        if (changePasswordDto != null){
            ResponseEntityDto changePassword = adminService.changePassword(changePasswordDto);
            return changePassword;
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        logger.info("admin.forgetPassword parameter = " + forgetPasswordDto);
        if (forgetPasswordDto != null){
            //find code by email
            AuthDto authDto = new AuthDto();
            authDto.setEmail(forgetPasswordDto.getEmail());
            authDto.setCode(forgetPasswordDto.getCode());
            authDto.setType("ForgetPassword");
            AuthDto authDto1 = authService.findCodeByEmail(authDto);
            if (authDto1 != null) {
                //otp already used
                AuthDto authDto2 = new AuthDto();
                authDto2.setId(authDto1.getId());
                authDto2.setStatus(2);
                authService.update(authDto2);
                forgetPasswordDto.setUserId(authDto1.getUserId());
                ResponseEntityDto forgetPassword = adminService.forgetPassword(forgetPasswordDto);
                return forgetPassword;
            }else {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.AUTH_WRONG);
            }
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody AdminDto adminDto) {
        logger.info("admin.get parameter = " + adminDto);
        if (adminDto != null){
            List<AdminDto> list = adminService.get(adminDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto update(@RequestBody AdminDto adminDto) {
        logger.info("admin.update parameter = " + adminDto);
        if (adminDto != null){
            int update = adminService.update(adminDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
