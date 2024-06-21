package com.project.teacher.serviceImpl;

import com.project.teacher.dao.TeacherBiodataDao;
import com.project.teacher.dto.BiodataDto;
import com.project.teacher.service.TeacherBiodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherBiodataServiceImpl implements TeacherBiodataService {

    @Autowired
    private TeacherBiodataDao teacherBiodataDao;

    @Override
    public int insert(BiodataDto biodataDto) {
        int insert = teacherBiodataDao.insert(biodataDto);
        return insert;
    }

    @Override
    public int update(BiodataDto biodataDto) {
        int insert = teacherBiodataDao.update(biodataDto);
        return insert;
    }


    @Override
    public BiodataDto getBiodataByTeacherId(BiodataDto biodataDto) {
        BiodataDto res = teacherBiodataDao.getBiodataByTeacherId(biodataDto);
        return res;
    }
}
