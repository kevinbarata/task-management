package com.project.resto.serviceImpl;

import com.project.resto.dao.BranchDao;
import com.project.resto.dao.SocialDao;
import com.project.resto.dto.BranchDto;
import com.project.resto.dto.SocialDto;
import com.project.resto.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialServiceImpl implements SocialService {

    @Autowired
    private SocialDao socialDao;

    @Override
    public int add(SocialDto socialDto) {
        socialDto.setStatus(1);
        int add = socialDao.add(socialDto);
        return add;
    }

    @Override
    public List<SocialDto> get (SocialDto socialDto) {
        List<SocialDto> list = socialDao.get(socialDto);
        return list;
    }

    @Override
    public int update(SocialDto socialDto) {
        int update = socialDao.update(socialDto);
        return update;
    }
}
