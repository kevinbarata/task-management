package com.project.task.controller;

import com.project.task.dto.*;
import com.project.task.service.UserService;
import com.project.task.service.AuthService;
import com.project.task.util.ErrorCodeEnum;
import com.project.task.util.ResponseEntityBuilder;
import com.project.task.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto register(@RequestBody UserDto userDto) {
        logger.info("user.register parameter = " + userDto);
        if (userDto != null){
            //find code by email
            AuthDto authDto = new AuthDto();
            authDto.setEmail(userDto.getEmail());
            authDto.setCode(userDto.getCode());
            authDto.setType("Register");
            AuthDto authDto1 = authService.findCodeByEmail(authDto);
            if (authDto1 != null) {
                //otp already used
                AuthDto authDto2 = new AuthDto();
                authDto2.setId(authDto1.getId());
                authDto2.setStatus(2);
                authService.update(authDto2);
                ResponseEntityDto register = userService.register(userDto);
                return register;
            } else {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.AUTH_WRONG);
            }
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto login(@RequestBody UserDto userDto) {
        logger.info("user.login parameter = " + userDto);
        if (userDto != null){
            ResponseEntityDto login = userService.login(userDto);
            return login;
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto logout(@RequestBody UserSessionDto userSessionDto) {
        logger.info("user.logout parameter = " + userSessionDto);
        if (userSessionDto != null){
            ResponseEntityDto logout = userService.logout(userSessionDto);
            return logout;
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        logger.info("user.changePassword parameter = " + changePasswordDto);
        if (changePasswordDto != null){
            ResponseEntityDto changePassword = userService.changePassword(changePasswordDto);
            return changePassword;
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        logger.info("user.forgetPassword parameter = " + forgetPasswordDto);
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
                ResponseEntityDto forgetPassword = userService.forgetPassword(forgetPasswordDto);
                return forgetPassword;
            }else {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.AUTH_WRONG);
            }
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody UserDto userDto) {
        logger.info("user.get parameter = " + userDto);
        if (userDto != null){
            List<UserDto> list = userService.get(userDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto update(@RequestBody UserDto userDto) {
        logger.info("user.update parameter = " + userDto);
        if (userDto != null){
            int update = userService.update(userDto);
            return ResponseEntityBuilder.buildNormalResponse(update);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
