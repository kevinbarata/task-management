package com.project.resto.serviceImpl;

import com.project.resto.dao.AssetDao;
import com.project.resto.dto.AssetDto;
import com.project.resto.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetDao assetDao;

    @Override
    public int add(AssetDto assetDto) {
        assetDto.setStatus(1);
        int add = assetDao.add(assetDto);
        return add;
    }

    @Override
    public List<AssetDto> get (AssetDto assetDto) {
        List<AssetDto> list = assetDao.get(assetDto);
        return list;
    }

    @Override
    public int update(AssetDto assetDto) {
        int update = assetDao.update(assetDto);
        return update;
    }
}
