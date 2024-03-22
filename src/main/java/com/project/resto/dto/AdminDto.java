package com.project.resto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminDto {
    private Long id;
    private String email;
    private String phone;
    private String password;
    private String username;
    private int status;
    private String remark;
    private String adminType;

    private Date createTime;
    private Date updateTime;
    private Date createTimeS;
    private Date createTimeE;

    private Long createTimeStart;
    private Long createTimeStartEnd;

    public Date getCreateTimeS() {
        return createTimeStart != null ? new Date(createTimeStart) : null;
    }

    public Date getCreateTimeE() {
        return createTimeStartEnd != null ? new Date(createTimeStartEnd) : null;
    }

}
