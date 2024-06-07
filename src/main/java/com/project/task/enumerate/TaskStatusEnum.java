package com.project.task.enumerate;

public enum TaskStatusEnum {

    INCOMPLETE("1","Task Incomplete!"),
    COMPLETED("2","Task Completed!");

    private String code;
    private String name;
    TaskStatusEnum(String code, String name){
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
