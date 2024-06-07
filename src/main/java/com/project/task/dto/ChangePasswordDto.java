package com.project.task.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChangePasswordDto {
    private Long id;
    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String newPasswordSecond;
    private String token;

    private Date createTime;
    private Date updateTime;


}
