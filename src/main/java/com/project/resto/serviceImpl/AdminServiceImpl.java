package com.project.resto.serviceImpl;

import com.project.resto.dao.AdminDao;
import com.project.resto.dao.AdminSessionDao;
import com.project.resto.dao.AssetDao;
import com.project.resto.dto.AdminDto;
import com.project.resto.dto.AdminSessionDto;
import com.project.resto.dto.AssetDto;
import com.project.resto.service.AdminService;
import com.project.resto.service.AssetService;
import com.project.resto.token.TokenGenerator;
import com.project.resto.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminSessionDao adminSessionDao;

    @Override
    public ResponseEntityDto register(AdminDto adminDto) {
        AdminDto adminDtoEmail = new AdminDto();
        adminDtoEmail.setEmail(adminDto.getEmail());
        List<AdminDto> checkByEmail = adminDao.get(adminDtoEmail);
        if (checkByEmail.size() != 0) {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.EMAIL_REGISTERED);
        }

        AdminDto adminDtoPhone = new AdminDto();
        adminDtoPhone.setPhone(adminDto.getPhone());
        List<AdminDto> checkByPhone = adminDao.get(adminDtoPhone);
        if (checkByPhone.size() != 0) {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PHONE_REGISTERED);
        }

        AdminDto adminDtoUsername = new AdminDto();
        adminDtoUsername.setUsername(adminDto.getUsername());
        List<AdminDto> checkByUsername = adminDao.get(adminDtoUsername);
        if (checkByUsername.size() != 0) {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.USERNAME_REGISTERED);
        }

        Boolean emailVerifier = EmailVerifier.isValidEmail(adminDto.getEmail());
        if (emailVerifier) {
            adminDto.setEmail(adminDto.getEmail());
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.EMAIL_WRONG);
        }

        String phoneValidate = RegStringUtils.validatePhoneFormat(adminDto.getPhone());
        if (phoneValidate.equals("true")){
            adminDto.setPhone(RegStringUtils.unifyPhoneFormat(adminDto.getPhone()));
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PHONE_WRONG);
        }

        Boolean passwordValidator = PasswordValidator.isValidPassword(adminDto.getPassword());
        if (passwordValidator) {
            adminDto.setPassword(DigestUtils.md5Hex(adminDto.getPassword()));
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PASSWORD_WRONG);
        }

        adminDto.setStatus(1);
        int add = adminDao.add(adminDto);
        Map<String,Object> res = new HashMap<>();
        if (add == 1){
            res.put("email",adminDto.getEmail());
            res.put("phone",adminDto.getPhone());
            res.put("username",adminDto.getUsername());
        }

        return ResponseEntityBuilder.buildNormalResponse(res);
    }

    @Override
    public ResponseEntityDto login(AdminDto adminDto) {
        // finding user
        AdminDto adminDtoEmail = new AdminDto();
        adminDtoEmail.setEmail(adminDto.getEmail());
        adminDtoEmail.setPassword(DigestUtils.md5Hex(adminDto.getPassword()));
        AdminDto checkByEmail = adminDao.getUserByPassword(adminDtoEmail);
        if (checkByEmail != null) {
            AdminSessionDto adminSessionDto = new AdminSessionDto();
            adminSessionDto.setUserId(checkByEmail.getId());
            adminSessionDto.setToken(TokenGenerator.generateToken());
            adminSessionDao.add(adminSessionDto);
            return ResponseEntityBuilder.buildNormalResponse(adminSessionDto);
        }

        AdminDto adminDtoPhone = new AdminDto();
        adminDtoPhone.setPhone(adminDto.getPhone());
        adminDtoEmail.setPassword(DigestUtils.md5Hex(adminDto.getPassword()));
        AdminDto checkByPhone = adminDao.getUserByPassword(adminDtoPhone);
        if (checkByPhone != null) {
            AdminSessionDto adminSessionDto = new AdminSessionDto();
            adminSessionDto.setUserId(checkByEmail.getId());
            adminSessionDto.setToken(TokenGenerator.generateToken());
            adminSessionDao.add(adminSessionDto);
            return ResponseEntityBuilder.buildNormalResponse(adminSessionDto);
        }

        AdminDto adminDtoUsername = new AdminDto();
        adminDtoUsername.setUsername(adminDto.getUsername());
        adminDtoEmail.setPassword(DigestUtils.md5Hex(adminDto.getPassword()));
        AdminDto checkByUsername = adminDao.getUserByPassword(adminDtoUsername);
        if (checkByUsername != null) {
            AdminSessionDto adminSessionDto = new AdminSessionDto();
            adminSessionDto.setUserId(checkByEmail.getId());
            adminSessionDto.setToken(TokenGenerator.generateToken());
            adminSessionDao.add(adminSessionDto);
            return ResponseEntityBuilder.buildNormalResponse(adminSessionDto);
        }

        return ResponseEntityBuilder.buildNormalResponse();
    }

    @Override
    public int validateSession (AdminSessionDto adminSessionDto) {
        int sessionDto = adminSessionDao.validateSession(adminSessionDto);
        if (sessionDto != 0){
            return 1;
        }
        return 0;
    }

    @Override
    public List<AdminDto> get (AdminDto adminDto) {
        List<AdminDto> list = adminDao.get(adminDto);
        return list;
    }

    @Override
    public int update(AdminDto adminDto) {
        int update = adminDao.update(adminDto);
        return update;
    }
}
