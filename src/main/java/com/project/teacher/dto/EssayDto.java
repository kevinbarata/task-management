package com.project.teacher.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EssayDto {
    private Long id;
    private Long essayId;
    private Long studentId;
    private Long teacherId;
    private String course;
    private String question;
    private String answer;
    private int status; // 1 = incomplete, 2 = completed
    private Date taskApplyTime;
    private Date taskCompletionTime;
    private Date createTime;
    private Date updateTime;

    private String token;

}
