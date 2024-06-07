package com.project.task.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthDto {
    private Long id;
    private Long userId;
    private String email;
    private int code;
    private String type;
    private int status; // 1 masuk // 2 dipakai
    private Date createTime;
    private Date updateTime;
}
