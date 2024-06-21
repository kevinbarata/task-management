package com.project.teacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BiodataDto {
    private Long id;

    private Long teacherId;
    private Long studentId;
    private String teacherName;
    private String gender;
    private String marriageStatus;
    private String education;

    private String bornCity;
    private String birthDay;
    private String province;
    private String city;
    private String district;

    private String address;
    private Date createTime;
    private Date updateTime;
    private String token;

}
