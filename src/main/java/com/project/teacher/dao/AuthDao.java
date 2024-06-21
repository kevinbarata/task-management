package com.project.teacher.dao;

import com.project.teacher.dto.AuthDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthDao {

    int add(AuthDto authDto);

    List<AuthDto> get(AuthDto authDto);

    int update(AuthDto authDto);

    AuthDto findCodeByEmail(AuthDto authDto);
}
