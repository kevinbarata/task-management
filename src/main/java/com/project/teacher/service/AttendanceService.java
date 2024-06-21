package com.project.teacher.service;

import com.project.teacher.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {

    int checkIn(AttendanceDto attendanceDto);

    int checkOut(AttendanceDto attendanceDto);

    List<AttendanceDto> getAll(AttendanceDto attendanceDto);

    AttendanceDto getTodayLog(AttendanceDto attendanceDto);
}
