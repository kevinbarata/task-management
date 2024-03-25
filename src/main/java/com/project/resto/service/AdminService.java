package com.project.resto.service;

import com.project.resto.dto.AdminDto;
import com.project.resto.dto.AdminSessionDto;
import com.project.resto.dto.AssetDto;
import com.project.resto.util.ResponseEntityDto;

import java.util.List;

public interface AdminService {

    ResponseEntityDto register(AdminDto adminDto);

    ResponseEntityDto login(AdminDto adminDto);

    List<AdminDto> get(AdminDto adminDto);

    int validateSession(AdminSessionDto adminSessionDto);

    int update(AdminDto adminDto);
}
