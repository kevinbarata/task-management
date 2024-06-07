package com.project.task.util;

public enum SuccessCodeEnum {

    CREATE_TASK_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 1, "Create a new task successfully!")
    , TASK_COMPLETED(SuccessCodeEnum.SUCCESS_CODE + 2, "Task mark as completed!")
    ;

    public static final String SUCCESS_CODE = "00000";

    private String code;
    private String message;

    SuccessCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}