package com.project.task.enumerate;

public enum AuthEnum {

    REGISTER("1","Register"),
    FORGET_PASSWORD("2","ForgetPassword"),
    ;

    private String code;
    private String name;
    AuthEnum(String code, String name){
        this.code=code;
        this.name=name;
    }

    public String getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }

}
