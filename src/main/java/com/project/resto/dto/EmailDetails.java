package com.project.resto.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailDetails {

    private String from;
    private String to;
    private String subject;
    private String message;
}