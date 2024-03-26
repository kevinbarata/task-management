package com.project.resto.dao;

import com.project.resto.dto.AdminDto;
import com.project.resto.dto.AdminSessionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminSessionDao {

    int add(AdminSessionDto adminSessionDto);

    AdminSessionDto validateSession(AdminSessionDto adminSessionDto);

    int killSession(AdminSessionDto adminSessionDto);

}
