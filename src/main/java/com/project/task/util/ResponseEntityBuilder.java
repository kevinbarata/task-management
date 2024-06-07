package com.project.task.util;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public final class ResponseEntityBuilder {

    public static final String RESPONSE_OK = "1";
    public static final String RESPONSE_FAIL = "0";
    public static final String RESPONSE_UN_NORMAL = "2";
    public static <T> ResponseEntityDto<T> buildErrorResponse(ErrorCodeEnum enums) {
        ResponseEntityDto<T> entity = new ResponseEntityDto<T>();
        entity.setStatus(RESPONSE_FAIL);
        entity.setError(String.valueOf(enums.getCode()));
        entity.setMsg(enums.getMessage());
        return entity;
    }

    public static <T> ResponseEntityDto<T> buildErrorResponse(String error, String message) {
        ResponseEntityDto<T> entity = new ResponseEntityDto<T>();
        entity.setStatus(RESPONSE_FAIL);
        if (StringUtils.isEmpty(error)) {
            error = "401";
        }
        entity.setError(error);
        entity.setMsg(message);
        return entity;
    }

    public static <T> ResponseEntityDto<T> buildNormalResponse() {
        return new ResponseEntityDto<T>(RESPONSE_OK, "00000000");
    }

    public static <T> ResponseEntityDto<T>  buildNormalResponse(T data) {
        ResponseEntityDto<T> entity = buildNormalResponse();
        entity.setData(data);
        return entity;
    }

    public static <T> ResponseEntityDto<T> buildNormalResponse(String msg,T data) {
        ResponseEntityDto<T> entity = buildNormalResponse();
        entity.setData(data);
        entity.setMsg(msg);
        return entity;
    }

    public static <T> ResponseEntityDto<T> buildUnNormalResponse(T data, ErrorCodeEnum enums) {
        ResponseEntityDto<T> entity = buildErrorResponse(enums);
        entity.setData(data);
        return entity;
    }

    public static <T> ResponseEntityDto<T> buildUnNormalResponseN(T data, ErrorCodeEnum enums) {
        ResponseEntityDto<T> entity = new ResponseEntityDto<T>(RESPONSE_UN_NORMAL, "00000000");
        entity.setData(data);
        entity.setError(String.valueOf(enums.getCode()));
        entity.setMsg(enums.getMessage());
        return entity;
    }

    public static <T> Boolean isSuccess(ResponseEntityDto<T> entity) {
        return entity.getStatus().equals(RESPONSE_OK);
    }

    public static <T> Boolean isSuccess2(ResponseEntityDto<T> entity) {
        return entity.getStatus().equals(RESPONSE_OK) && entity.getError().equals("00000000");
    }

    public static <T> Boolean isSuccess3(ResponseEntityDto<T> entity) {
        return entity.getStatus().equals(RESPONSE_OK) && entity.getError().equals("200") && entity.getData() != null;
    }

    public static <T extends Serializable> T getEntity(ResponseEntityDto<T> entity) {
        if (isSuccess(entity)) {
            return (T) entity.getData();

        }
        return null;
    }

}
