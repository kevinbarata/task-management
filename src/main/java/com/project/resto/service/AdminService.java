package com.project.resto.service;

import com.project.resto.dto.*;
import com.project.resto.util.ResponseEntityDto;

import java.util.List;

public interface AdminService {

    ResponseEntityDto register(AdminDto adminDto);

    ResponseEntityDto login(AdminDto adminDto);

    ResponseEntityDto logout(AdminSessionDto adminSessionDto);

    ResponseEntityDto changePassword(ChangePasswordDto changePasswordDto);

    ResponseEntityDto forgetPassword(ForgetPasswordDto forgetPasswordDto);

    List<AdminDto> get(AdminDto adminDto);

    int validateSession(AdminSessionDto adminSessionDto);

    int update(AdminDto adminDto);
}
