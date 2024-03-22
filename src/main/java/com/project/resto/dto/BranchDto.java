package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BranchDto {
    private Long id;

    private String name;
    private String bornDate;
    private String registrationNo;
    private String owner;
    private String province;

    private String city;
    private String district;
    private String urbanVillage;
    private String postcode;
    private String address;

    private String remark;
    private int status;
    private Date createTime;
    private Date updateTime;

}
