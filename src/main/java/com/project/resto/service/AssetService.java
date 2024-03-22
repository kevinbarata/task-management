package com.project.resto.service;

import com.project.resto.dto.AssetDto;

import java.util.List;

public interface AssetService {

    int add(AssetDto assetDto);

    List<AssetDto> get(AssetDto assetDto);

    int update(AssetDto assetDto);
}
