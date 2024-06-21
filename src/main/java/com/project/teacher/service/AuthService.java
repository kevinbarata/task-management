package com.project.teacher.service;

import com.project.teacher.dto.AuthDto;

import java.util.List;

public interface AuthService {

    int add(AuthDto authDto);

    List<AuthDto> get(AuthDto authDto);

    int update(AuthDto authDto);

    AuthDto findCodeByEmail(AuthDto authDto);
}
