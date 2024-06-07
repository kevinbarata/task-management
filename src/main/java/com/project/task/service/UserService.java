package com.project.task.service;

import com.project.task.dto.*;
import com.project.task.util.ResponseEntityDto;

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
