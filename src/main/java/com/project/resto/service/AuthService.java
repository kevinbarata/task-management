package com.project.resto.service;

import com.project.resto.dto.AssetDto;
import com.project.resto.dto.AuthDto;

import java.util.List;

public interface AuthService {

    int add(AuthDto authDto);

    List<AuthDto> get(AuthDto authDto);

    int update(AuthDto authDto);

    AuthDto findCodeByEmail(AuthDto authDto);
}
