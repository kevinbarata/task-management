package com.project.resto.util;

/**
 * 封装分页数据
 */

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Page implements Serializable{

    public static String DEFAULT_PAGESIZE = "10";
    private int pageNo;          //当前页码
    private int pageSize;        //每页行数
    private int totalRecord;      //总记录数
    private int totalPage;        //总页数
    private Map<String, Object> params;  //查询条件
    private Map<String, List<String>> paramLists;  //数组查询条件

    private int offset;//开始查询数

    public Page(Map<String, Object> params) {
        this.params = params;
    }

    public Page() {
        pageNo = 1;
        pageSize = Integer.valueOf(DEFAULT_PAGESIZE);
        totalRecord = 0;
        totalPage = 0;
        params = new HashMap();
        paramLists = new HashMap();
    }

    public Page(int pageNo ,int pageSize,int totalRecord) {
        if(pageNo <1){
            this.pageNo =1;
        }else{
            this.pageNo = pageNo;
        }
        if(pageSize<1){
            this.pageSize = Integer.valueOf(DEFAULT_PAGESIZE);
        }else{
            this.pageSize = pageSize;
        }
        this.totalRecord = totalRecord;
    }

    public int getOffset() {
        return (this.pageNo-1) * this.pageSize;
    }

}