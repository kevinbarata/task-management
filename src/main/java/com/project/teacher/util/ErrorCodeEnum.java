package com.project.teacher.util;

public enum ErrorCodeEnum {

    PARAM_IS_NULL(ErrorCodeEnum.ERROR_CODE + 1, "Parameter is null!")
    , PARAM_VALUE_ERROR(ErrorCodeEnum.ERROR_CODE + 2, "Parameter value exception!")
    , PHONE_WRONG(ErrorCodeEnum.ERROR_CODE + 3, "Wrong phone!")
    , EMAIL_WRONG(ErrorCodeEnum.ERROR_CODE + 4, "Wrong email!")
    , PASSWORD_WRONG(ErrorCodeEnum.ERROR_CODE + 5, "The password must start with an uppercase letter and contain numbers!")
    , EMAIL_REGISTERED(ErrorCodeEnum.ERROR_CODE + 6, "Email has been registered!")
    , PHONE_REGISTERED(ErrorCodeEnum.ERROR_CODE + 7, "Phone has been registered!")
    , USERNAME_REGISTERED(ErrorCodeEnum.ERROR_CODE + 8, "Username has been registered!")
    , AUTH_WRONG(ErrorCodeEnum.ERROR_CODE + 9, "Wrong Auth!")
    , SEND_EMAIL_FAILED(ErrorCodeEnum.ERROR_CODE + 10, "Send Email OTP failed!")
    ,UNAUTHORIZED(ErrorCodeEnum.ERROR_CODE + 11, "Unauthorized")
    ,CHECK_IN_DONE_TODAY(ErrorCodeEnum.ERROR_CODE + 11, "Your Account has been check in today!")
    ,CHECK_IN_UN_DONE_TODAY(ErrorCodeEnum.ERROR_CODE + 11, "You are not checked in yet!")
    ;

    public static final String ERROR_CODE = "01000";

    private String code;
    private String message;

    ErrorCodeEnum(String code, String message) {
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