package com.project.task.serviceImpl;

import com.project.task.dao.AuthDao;
import com.project.task.dto.AuthDto;
import com.project.task.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public int add(AuthDto authDto) {
        int add = authDao.add(authDto);
        return add;
    }

    @Override
    public List<AuthDto> get (AuthDto authDto) {
        List<AuthDto> list = authDao.get(authDto);
        return list;
    }

    @Override
    public int update(AuthDto authDto) {
        int update = authDao.update(authDto);
        return update;
    }

    @Override
    public AuthDto findCodeByEmail(AuthDto authDto) {
        AuthDto code = authDao.findCodeByEmail(authDto);
        return code;
    }
}
