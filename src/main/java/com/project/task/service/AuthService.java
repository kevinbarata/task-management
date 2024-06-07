package com.project.task.service;

import com.project.task.dto.AuthDto;

import java.util.List;

public interface AuthService {

    int add(AuthDto authDto);

    List<AuthDto> get(AuthDto authDto);

    int update(AuthDto authDto);

    AuthDto findCodeByEmail(AuthDto authDto);
}
