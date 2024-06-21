package com.project.teacher.serviceImpl;

import com.project.teacher.dto.BiodataDto;
import com.project.teacher.dto.StudentAttendanceDto;
import com.project.teacher.dto.StudentDto;
import com.project.teacher.service.StudentService;
import com.project.teacher.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class StudentServiceHystrix implements FallbackFactory<StudentService> {

    Logger logger = LoggerFactory.getLogger(StudentServiceHystrix.class);

    @Override
    public StudentService create(Throwable e) {
        return new StudentService() {
            @Override
            public ResponseEntityDto getBiodataByStudentId(@RequestBody BiodataDto dto) {
                logger.error("Got an error when executing 'getBiodataByStudentId(BiodataDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto getAllStudent(@RequestBody StudentDto dto) {
                logger.error("Got an error when executing 'getAllStudent(StudentDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto getStudentAttendance(@RequestBody StudentAttendanceDto dto) {
                logger.error("Got an error when executing 'getStudentAttendance(StudentAttendanceDto dto)'!!!",e);
                return null;
            }

        };
    }

}
