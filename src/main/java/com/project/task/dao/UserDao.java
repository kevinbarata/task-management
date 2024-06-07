package com.project.task.dao;

import com.project.task.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int add(UserDto userDto);

    List<UserDto> get(UserDto userDto);

    int update(UserDto userDto);

    UserDto getUserByPassword(UserDto userDto);

    int changePassword(UserDto userDto);
}
