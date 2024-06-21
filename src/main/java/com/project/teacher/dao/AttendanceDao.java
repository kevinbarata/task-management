package com.project.teacher.dao;

import com.project.teacher.dto.AttendanceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceDao {

    int checkIn(AttendanceDto attendanceDto);

    int checkOut(AttendanceDto attendanceDto);

    List<AttendanceDto> getAll(AttendanceDto attendanceDto);

    AttendanceDto getTodayLog(AttendanceDto attendanceDto);
}
