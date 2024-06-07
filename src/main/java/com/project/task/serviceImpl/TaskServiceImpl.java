package com.project.task.serviceImpl;

import com.project.task.dao.TaskDao;
import com.project.task.dto.TaskDto;
import com.project.task.enumerate.TaskStatusEnum;
import com.project.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public int createNewTask(TaskDto taskDto) {
        taskDto.setStatus(Integer.parseInt(TaskStatusEnum.INCOMPLETE.getCode()));
        taskDto.setTaskApplyTime(new Date());
        int insert = taskDao.createNewTask(taskDto);
        return insert;
    }

    @Override
    public int markTaskAsCompleted(TaskDto taskDto) {
        taskDto.setStatus(Integer.parseInt(TaskStatusEnum.COMPLETED.getCode()));
        taskDto.setCompletionBy(taskDto.getCompletionBy());
        taskDto.setTaskCompletionTime(new Date());
        int update = taskDao.markTaskAsCompleted(taskDto);
        return update;
    }

    @Override
    public List<TaskDto> getAllTask (TaskDto taskDto) {
        List<TaskDto> list = taskDao.getAllTask(taskDto);
        return list;
    }
}
