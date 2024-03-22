package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MenuCategoryDto {
    private Long id;
    private String name;
    private String remark;
    private int status;
    private Date createTime;
    private Date updateTime;
}
