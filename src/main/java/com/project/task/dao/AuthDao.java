package com.project.task.dao;

import com.project.task.dto.AuthDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthDao {

    int add(AuthDto authDto);

    List<AuthDto> get(AuthDto authDto);

    int update(AuthDto authDto);

    AuthDto findCodeByEmail(AuthDto authDto);
}
