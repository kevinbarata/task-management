package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AssetDto {
    private Long id;
    private String name;
    private String merk;
    private int qty;
    private String remark;
    private int status;
    private Date createTime;
    private Date updateTime;

}
