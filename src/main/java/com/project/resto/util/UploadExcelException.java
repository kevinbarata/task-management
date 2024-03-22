package com.project.resto.util;

import lombok.Data;

@Data
public class UploadExcelException extends Exception {

    private String errorCode;
    private String message;

    public UploadExcelException(String errorCode, String message) {
        this.errorCode=errorCode;
        this.message=message;
    }
}
