package com.project.task.controller;

import com.project.task.config.JwtUtil;
import com.project.task.dao.UserSessionDao;
import com.project.task.dto.TaskDto;
import com.project.task.service.TaskService;
import com.project.task.util.ErrorCodeEnum;
import com.project.task.util.ResponseEntityBuilder;
import com.project.task.util.ResponseEntityDto;
import com.project.task.util.SuccessCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/assignment")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserSessionDao userSessionDao;

    //Create a new task.
    @RequestMapping(value = "/createNewTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto createNewTask(@RequestBody TaskDto taskDto) {
        logger.info("task.createNewTask parameter = " + taskDto);
        if (taskDto != null) {
            Long userId = taskDto.getUserId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(taskDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            int add = taskService.createNewTask(taskDto);
            if (add == 1) {
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.CREATE_TASK_SUCCESS.getMessage(), add);
            }
            return ResponseEntityBuilder.buildNormalResponse();
        } else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    //Mark a task as completed.
    @RequestMapping(value = "/markTaskAsCompleted", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto markTaskAsCompleted(@RequestBody TaskDto taskDto) {
        logger.info("task.markTaskAsCompleted parameter = " + taskDto);
        if (taskDto != null){
            Long userId = taskDto.getUserId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(taskDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            int update = taskService.markTaskAsCompleted(taskDto);
            if (update == 1){
                return ResponseEntityBuilder.buildNormalResponse(SuccessCodeEnum.TASK_COMPLETED.getMessage(),update);
            }
            return ResponseEntityBuilder.buildNormalResponse();
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

    //Retrieve a list of tasks.
    //with filters
    @RequestMapping(value = "/getAllTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntityDto getAllTask(@RequestBody TaskDto taskDto ) {
        logger.info("task.getAllTask parameter = " + taskDto);
        if (taskDto != null){
            Long userId = taskDto.getUserId();
            if (userId == null) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            } else if (!JwtUtil.validateToken(taskDto.getToken(), String.valueOf(userId),userSessionDao)) {
                return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
            }
            List<TaskDto> list = taskService.getAllTask(taskDto);
            return ResponseEntityBuilder.buildNormalResponse(list);
        }else {
            return ResponseEntityBuilder.buildErrorResponse(ErrorCodeEnum.PARAM_VALUE_ERROR);
        }
    }

}
