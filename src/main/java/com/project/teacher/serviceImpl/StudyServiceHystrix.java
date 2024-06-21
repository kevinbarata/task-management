package com.project.teacher.serviceImpl;

import com.project.teacher.dto.*;
import com.project.teacher.service.StudentService;
import com.project.teacher.service.StudyService;
import com.project.teacher.util.ResponseEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class StudyServiceHystrix implements FallbackFactory<StudyService> {

    Logger logger = LoggerFactory.getLogger(StudyServiceHystrix.class);

    @Override
    public StudyService create(Throwable e) {
        return new StudyService() {
            @Override
            public ResponseEntityDto createELearningMaterial(@RequestBody ELearningDto dto) {
                logger.error("Got an error when executing 'createELearningMaterial(ELearningDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto getAllMaterial(@RequestBody ELearningDto dto) {
                logger.error("Got an error when executing 'getAllMaterial(ELearningDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto createNewEssay(@RequestBody EssayDto dto) {
                logger.error("Got an error when executing 'createNewEssay(EssayDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto getAllEssay(@RequestBody EssayDto dto) {
                logger.error("Got an error when executing 'getAllEssay(EssayDto dto)'!!!",e);
                return null;
            }

            @Override
            public ResponseEntityDto markEssayAsCompleted(@RequestBody EssayDto dto) {
                logger.error("Got an error when executing 'markEssayAsCompleted(EssayDto dto)'!!!",e);
                return null;
            }

        };
    }

}
