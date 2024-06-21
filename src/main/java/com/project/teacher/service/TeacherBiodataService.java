package com.project.teacher.service;

import com.project.teacher.dto.AttendanceDto;
import com.project.teacher.dto.BiodataDto;

import java.util.List;

public interface TeacherBiodataService {

    int insert(BiodataDto biodataDto);

    int update(BiodataDto biodataDto);

    BiodataDto getBiodataByTeacherId(BiodataDto biodataDto);
}
