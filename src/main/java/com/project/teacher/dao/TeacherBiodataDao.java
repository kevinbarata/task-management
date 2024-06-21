package com.project.teacher.dao;

import com.project.teacher.dto.AttendanceDto;
import com.project.teacher.dto.BiodataDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherBiodataDao {

    int insert(BiodataDto dto);

    int update(BiodataDto dto);

    BiodataDto getBiodataByTeacherId(BiodataDto dto);
}
