package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MenuDto {
    private Long id;
    private String category;
    private String name;
    private double price;
    private String remark;
    private int status;
    private Date createTime;
    private Date updateTime;
}
