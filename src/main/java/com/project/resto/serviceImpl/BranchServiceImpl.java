package com.project.resto.serviceImpl;

import com.project.resto.dao.AssetDao;
import com.project.resto.dao.BranchDao;
import com.project.resto.dto.AssetDto;
import com.project.resto.dto.BranchDto;
import com.project.resto.service.AssetService;
import com.project.resto.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchDao branchDao;

    @Override
    public int add(BranchDto branchDto) {
        branchDto.setStatus(1);
        int add = branchDao.add(branchDto);
        return add;
    }

    @Override
    public List<BranchDto> get (BranchDto branchDto) {
        List<BranchDto> list = branchDao.get(branchDto);
        return list;
    }

    @Override
    public int update(BranchDto branchDto) {
        int update = branchDao.update(branchDto);
        return update;
    }
}
