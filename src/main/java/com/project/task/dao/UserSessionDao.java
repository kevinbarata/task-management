package com.project.task.dao;

import com.project.task.dto.UserSessionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSessionDao {

    int add(UserSessionDto userSessionDto);

    UserSessionDto validateSession(UserSessionDto userSessionDto);

    int killSession(UserSessionDto userSessionDto);

    int isValidToken(UserSessionDto userSessionDto);
}
