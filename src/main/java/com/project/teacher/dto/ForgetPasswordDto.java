package com.project.teacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ForgetPasswordDto {
    private Long id;
    private Long userId;
    private String email;
    private int code;
    private String newPassword;
    private String newPasswordSecond;

    private Date createTime;
    private Date updateTime;


}
