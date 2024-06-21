package com.project.teacher.service;

import com.project.teacher.dto.*;
import com.project.teacher.util.ResponseEntityDto;

import java.util.List;

public interface UserService {

    ResponseEntityDto register(UserDto userDto);

    ResponseEntityDto login(UserDto userDto);

    ResponseEntityDto logout(UserSessionDto userSessionDto);

    ResponseEntityDto changePassword(ChangePasswordDto changePasswordDto);

    ResponseEntityDto forgetPassword(ForgetPasswordDto forgetPasswordDto);

    List<UserDto> get(UserDto userDto);

    int validateSession(UserSessionDto userSessionDto);

    int update(UserDto userDto);
}
