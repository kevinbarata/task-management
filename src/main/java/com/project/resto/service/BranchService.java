package com.project.resto.service;

import com.project.resto.dto.AssetDto;
import com.project.resto.dto.BranchDto;

import java.util.List;

public interface BranchService {

    int add(BranchDto branchDto);

    List<BranchDto> get(BranchDto branchDto);

    int update(BranchDto branchDto);
}
