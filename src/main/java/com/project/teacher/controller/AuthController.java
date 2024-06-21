package com.project.teacher.controller;

import com.project.teacher.dto.AuthDto;
import com.project.teacher.dto.EmailDetails;
import com.project.teacher.service.AuthService;
import com.project.teacher.service.EmailSenderService;
import com.project.teacher.util.ErrorCodeEnum;
import com.project.teacher.util.RandomNumberGenerator;
import com.project.teacher.util.ResponseEntityBuilder;
import com.project.teacher.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value = "/request", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto request(@RequestBody AuthDto authDto) {
        logger.info("auth.request parameter = " + authDto);
        if (authDto != null){
            AuthDto add = new AuthDto();
            add.setStatus(1);
            add.setEmail(authDto.getEmail());
            add.setType(authDto.getType());
            int code  = RandomNumberGenerator.sixNumber();
            logger.info("code" + code);
            add.setCode(code);
            EmailDetails emailDetails = new EmailDetails();
                Date today = new Date();
            emailDetails.setSubject(authDto.getType() + " Teacher Management System "+ today);
            emailDetails.setTo(authDto.getEmail());
            String htmlMsg = "<p style='margin:5px 0;font-weight:400;font-size:16pt;'>This is your code :</p>";
                htmlMsg += "<br>";
                htmlMsg += "<p style='margin:5px 0 15px;'>" + code + "</p>";
                htmlMsg += "<br>";
                htmlMsg += "<p style='margin:5px 0 15px;'> Thank You, </p>";
                htmlMsg += "<p style='margin:5px 0 15px;'>- Teacher Authentication System -</p>";
            emailDetails.setMessage(htmlMsg);
                try {
                    int res = emailSenderService.sendEmail(emailDetails);
                    if (res == 1) {
                        authService.add(add);
                    }
                }catch (Exception e){
                    return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.SEND_EMAIL_FAILED.getMessage(),authDto.getEmail());
                }
                AuthDto res = new AuthDto();
                res.setEmail(add.getEmail());
                res.setCode(add.getCode());
            return ResponseEntityBuilder.buildNormalResponse(res);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto get(@RequestBody AuthDto authDto) {
        logger.info("auth.getAll parameter = " + authDto);
        if (authDto != null){
            List<AuthDto> list = authService.get(authDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
