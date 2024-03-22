package com.project.resto.dao;

import com.project.resto.dto.BranchDto;
import com.project.resto.dto.SocialDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SocialDao {

    int add(SocialDto socialDto);

    List<SocialDto> get(SocialDto socialDto);

    int update(SocialDto branchDto);
}
