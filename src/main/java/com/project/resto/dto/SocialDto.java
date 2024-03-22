package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SocialDto {
    private Long id;
    private String platForm;
    private String name;
    private String link;
    private String remark;
    private int status;
    private Date createTime;
    private Date updateTime;

}
