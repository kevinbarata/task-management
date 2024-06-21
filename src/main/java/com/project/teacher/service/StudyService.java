package com.project.teacher.service;

import com.project.teacher.dto.*;
import com.project.teacher.serviceImpl.StudyServiceHystrix;
import com.project.teacher.util.ResponseEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "STUDY-SERVICE",fallbackFactory = StudyServiceHystrix.class)
public interface StudyService {

    @RequestMapping(value = "/study/learning/createELearningMaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto createELearningMaterial(@RequestBody ELearningDto dto);

    @RequestMapping(value = "/study/learning/getAllMaterial", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllMaterial(@RequestBody ELearningDto dto);

    @RequestMapping(value = "/study/task/createNewEssay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto createNewEssay(@RequestBody EssayDto dto);

    @RequestMapping(value = "/study/task/getAllEssay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllEssay(@RequestBody EssayDto dto);

    @RequestMapping(value = "/study/task/markEssayAsCompleted", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto markEssayAsCompleted(@RequestBody EssayDto dto);

}
