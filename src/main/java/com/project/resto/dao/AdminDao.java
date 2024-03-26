package com.project.resto.dao;

import com.project.resto.dto.AdminDto;
import com.project.resto.dto.AssetDto;
import com.project.resto.dto.ChangePasswordDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDao {

    int add(AdminDto adminDto);

    List<AdminDto> get(AdminDto adminDto);

    int update(AdminDto adminDto);

    AdminDto getUserByPassword(AdminDto adminDto);

    int changePassword(AdminDto adminDto);
}
