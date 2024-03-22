package com.project.resto.dao;

import com.project.resto.dto.AssetDto;
import com.project.resto.dto.BranchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BranchDao {

    int add(BranchDto branchDto);

    List<BranchDto> get(BranchDto branchDto);

    int update(BranchDto branchDto);
}
