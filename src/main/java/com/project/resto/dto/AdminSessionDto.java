package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminSessionDto {
    private Long id;
    private Long userId;
    private String token;

    private Date createTime;
    private Date updateTime;

}
