package com.project.resto.service;

import com.project.resto.dto.BranchDto;
import com.project.resto.dto.SocialDto;

import java.util.List;

public interface SocialService {

    int add(SocialDto socialDto);

    List<SocialDto> get(SocialDto socialDto);

    int update(SocialDto socialDto);
}
