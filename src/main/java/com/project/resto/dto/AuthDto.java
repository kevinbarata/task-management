package com.project.resto.dto;

import com.project.resto.enumerate.AuthEnum;
import lombok.Data;

import java.util.Date;

@Data
public class AuthDto {
    private Long id;
    private String email;
    private int code;
    private String type;
    private int status; // 1 masuk // 2 dipakai
    private Date createTime;
    private Date updateTime;
}
