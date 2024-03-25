package com.project.resto.enumerate;

public enum AuthEnum {

    REGISTER("1","Register"),
    LOGIN("1","login");

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
