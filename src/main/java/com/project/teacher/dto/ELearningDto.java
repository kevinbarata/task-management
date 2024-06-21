package com.project.teacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ELearningDto {
    private Long id;
    private Long teacherId;
    private Long learningId;

    private String course;
    private String subject;
    private String text;
    private Date createTime;
    private Date updateTime;

    private String token;

}
