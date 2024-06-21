package com.project.teacher.util;

public enum SuccessCodeEnum {

    CREATE_TASK_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 1, "Create a new task successfully!")
    , TASK_COMPLETED(SuccessCodeEnum.SUCCESS_CODE + 2, "Task mark as completed!")
    , UPDATE_BIODATA_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 3, "Update Biodata Success!!")
    , CHECK_IN_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 4, "Check In success!!")
    , CHECK_OUT_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 5, "Check Out success!!")
    ,CREATE_ELEARNING_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 6, "Create a new material successfully!")
    ,UPDATE_ELEARNING_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 7, "update material successfully!")
    ,CREATE_ESSAY_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 6, "Create a new essay successfully!")
    ,UPDATE_ESSAY_SUCCESS(SuccessCodeEnum.SUCCESS_CODE + 7, "update essay successfully!")
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