package com.project.resto.dao;

import com.project.resto.dto.AssetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetDao {

    int add(AssetDto assetDto);

    List<AssetDto> get(AssetDto assetDto);

    int update(AssetDto assetDto);
}
