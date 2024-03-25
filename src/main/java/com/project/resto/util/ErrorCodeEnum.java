package com.project.resto.util;

public enum ErrorCodeEnum {

    PARAM_IS_NULL(ErrorCodeEnum.ERROR_CODE + 1, "param is null")
    , PARAM_VALUE_ERROR(ErrorCodeEnum.ERROR_CODE + 2, "Parameter value exception")
    , PHONE_WRONG(ErrorCodeEnum.ERROR_CODE + 3, "No Telp salah")
    , EMAIL_WRONG(ErrorCodeEnum.ERROR_CODE + 4, "Email salah")
    , PASSWORD_WRONG(ErrorCodeEnum.ERROR_CODE + 5, "Password harus berawalan huruf besar dan mengandung angka")
    , EMAIL_REGISTERED(ErrorCodeEnum.ERROR_CODE + 6, "Email telah terdaftar")
    , PHONE_REGISTERED(ErrorCodeEnum.ERROR_CODE + 7, "No. Hp telah terdaftar")
    , USERNAME_REGISTERED(ErrorCodeEnum.ERROR_CODE + 8, "Username telah terdaftar")
    , AUTH_WRONG(ErrorCodeEnum.ERROR_CODE + 9, "Authentikasi salah")
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