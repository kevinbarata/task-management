package com.project.task.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private Long id;
    private Long taskId;
    private Long userId;
    private String taskName;
    private String description;
    private String requestFor;
    private String requestBy;
    private int status; // 1 = incomplete, 2 = completed
    private Date taskApplyTime;
    private String completionBy;
    private Date taskCompletionTime;
    private Date createTime;
    private Date updateTime;

    private String token;

}
