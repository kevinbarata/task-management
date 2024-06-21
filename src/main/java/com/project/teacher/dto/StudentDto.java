package com.project.teacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {
    private Long id;
    private Long teacherId;

    private Long studentId;
    private String username;
    private String email;
    private String phone;
    private String studentName;

    private String major;
    private String grade;
    private String gender;
    private String status;

    private Date createTime;
    private Date updateTime;
    private String token;

}
