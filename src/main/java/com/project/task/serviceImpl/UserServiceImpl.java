package com.project.task.serviceImpl;

import com.project.task.config.JwtUtil;
import com.project.task.dao.UserDao;
import com.project.task.dao.UserSessionDao;
import com.project.task.dto.*;
import com.project.task.service.UserService;
import com.project.task.config.TokenGenerator;
import com.project.task.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public ResponseEntityDto register(UserDto userDto) {
        UserDto userDtoEmail = new UserDto();
        userDtoEmail.setEmail(userDto.getEmail());
        List<UserDto> checkByEmail = userDao.get(userDtoEmail);
        if (checkByEmail.size() != 0) {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.EMAIL_REGISTERED);
        }

        UserDto userDtoPhone = new UserDto();
        userDtoPhone.setPhone(userDto.getPhone());
        List<UserDto> checkByPhone = userDao.get(userDtoPhone);
        if (checkByPhone.size() != 0) {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PHONE_REGISTERED);
        }

        UserDto userDtoUsername = new UserDto();
        userDtoUsername.setUsername(userDto.getUsername());
        List<UserDto> checkByUsername = userDao.get(userDtoUsername);
        if (checkByUsername.size() != 0) {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.USERNAME_REGISTERED);
        }

        Boolean emailVerifier = EmailVerifier.isValidEmail(userDto.getEmail());
        if (emailVerifier) {
            userDto.setEmail(userDto.getEmail());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.EMAIL_WRONG);
        }

        String phoneValidate = RegStringUtils.validatePhoneFormat(userDto.getPhone());
        if (phoneValidate.equals("true")){
            userDto.setPhone(RegStringUtils.unifyPhoneFormat(userDto.getPhone()));
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PHONE_WRONG);
        }

        Boolean passwordValidator = PasswordValidator.isValidPassword(userDto.getPassword());
        if (passwordValidator) {
            userDto.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PASSWORD_WRONG);
        }

        userDto.setStatus(1);
        int add = userDao.add(userDto);
        Map<String,Object> res = new HashMap<>();
        if (add == 1){
            res.put("email", userDto.getEmail());
            res.put("phone", userDto.getPhone());
            res.put("username", userDto.getUsername());
        }

        return ResponseEntityBuilder.buildNormalResponse(res);
    }

    @Override
    public ResponseEntityDto login(UserDto userDto) {
        // finding user
        UserDto userDtoEmail = new UserDto();
        userDtoEmail.setEmail(userDto.getEmail());
        userDtoEmail.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        UserDto checkByEmail = userDao.getUserByPassword(userDtoEmail);
        if (checkByEmail != null) {
            return createUserSession(checkByEmail);
        }

        UserDto userDtoPhone = new UserDto();
        userDtoPhone.setPhone(userDto.getPhone());
        userDtoEmail.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        UserDto checkByPhone = userDao.getUserByPassword(userDtoPhone);
        if (checkByPhone != null) {
            UserSessionDto userSessionDto = new UserSessionDto();
            userSessionDto.setUserId(checkByEmail.getId());
            userSessionDto.setToken(TokenGenerator.generateToken());
            userSessionDao.add(userSessionDto);
            return ResponseEntityBuilder.buildNormalResponse(userSessionDto);
        }

        UserDto userDtoUsername = new UserDto();
        userDtoUsername.setUsername(userDto.getUsername());
        userDtoEmail.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        UserDto checkByUsername = userDao.getUserByPassword(userDtoUsername);
        if (checkByUsername != null) {
            UserSessionDto userSessionDto = new UserSessionDto();
            userSessionDto.setUserId(checkByEmail.getId());
            userSessionDto.setToken(TokenGenerator.generateToken());
            userSessionDao.add(userSessionDto);
            return ResponseEntityBuilder.buildNormalResponse(userSessionDto);
        }

        return ResponseEntityBuilder.buildNormalResponse();
    }

    private ResponseEntityDto createUserSession(UserDto user) {
        String token = JwtUtil.generateToken(String.valueOf(user.getId()));
//        String token = "ad2312321";
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUserId(user.getId());
        userSessionDto.setToken(token);
        userSessionDao.add(userSessionDto);
        return ResponseEntityBuilder.buildNormalResponse(userSessionDto);
    }

    @Override
    public ResponseEntityDto logout(UserSessionDto userSessionDto) {
        int kill = userSessionDao.killSession(userSessionDto);
        return ResponseEntityBuilder.buildNormalResponse(kill);
    }

    @Override
    public ResponseEntityDto changePassword(ChangePasswordDto changePasswordDto) {
        // getuser by token
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUserId(changePasswordDto.getUserId());
        userSessionDto.setToken(changePasswordDto.getToken());
        UserSessionDto validateSession = userSessionDao.validateSession(userSessionDto);
        // find user By password
        UserDto userDto = new UserDto();
        userDto.setId(changePasswordDto.getId());
        userDto.setPassword(DigestUtils.md5Hex(changePasswordDto.getOldPassword()));
        UserDto matchPassword = userDao.getUserByPassword(userDto);
        // match new password
        String newPassword = matchPassword.getPassword();
        int changePassword = 0;
        if (changePasswordDto.getNewPassword().equals(changePasswordDto.getNewPasswordSecond()) && validateSession != null && matchPassword != null){
            newPassword = DigestUtils.md5Hex(changePasswordDto.getNewPassword());
            UserDto change = new UserDto();
            change.setPassword(newPassword);
            change.setId(matchPassword.getId());
            changePassword = userDao.changePassword(change);
        }
        return ResponseEntityBuilder.buildNormalResponse(changePassword);
    }

    @Override
    public ResponseEntityDto forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        // match new password
        String newPassword = "";
        int changePassword = 0;
        if (forgetPasswordDto.getNewPassword().equals(forgetPasswordDto.getNewPasswordSecond())){
            newPassword = DigestUtils.md5Hex(forgetPasswordDto.getNewPassword());
            UserDto change = new UserDto();
            change.setPassword(newPassword);
            change.setId(forgetPasswordDto.getUserId());
            changePassword = userDao.changePassword(change);
        }
        return ResponseEntityBuilder.buildNormalResponse(changePassword);
    }


    @Override
    public int validateSession (UserSessionDto userSessionDto) {
        UserSessionDto sessionDto = userSessionDao.validateSession(userSessionDto);
        if (sessionDto != null){
            return 1;
        }
        return 0;
    }

    @Override
    public List<UserDto> get (UserDto userDto) {
        List<UserDto> list = userDao.get(userDto);
        return list;
    }

    @Override
    public int update(UserDto userDto) {
        int update = userDao.update(userDto);
        return update;
    }
}
