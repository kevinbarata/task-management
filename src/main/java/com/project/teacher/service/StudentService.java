package com.project.teacher.service;

import com.project.teacher.dto.BiodataDto;
import com.project.teacher.dto.StudentAttendanceDto;
import com.project.teacher.dto.StudentDto;
import com.project.teacher.serviceImpl.StudentServiceHystrix;
import com.project.teacher.util.ResponseEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "STUDENT-SERVICE",fallbackFactory = StudentServiceHystrix.class)
public interface StudentService {

    @RequestMapping(value = "/student/biodata/getBiodataByStudentId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getBiodataByStudentId(@RequestBody BiodataDto dto);

    @RequestMapping(value = "/student/user/getAllStudent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllStudent(@RequestBody StudentDto dto);

    @RequestMapping(value = "/student/attendance/getStudentAttendance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getStudentAttendance(@RequestBody StudentAttendanceDto dto);

}
