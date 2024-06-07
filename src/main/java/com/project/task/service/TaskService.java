package com.project.task.service;

import com.project.task.dto.TaskDto;

import java.util.List;

public interface TaskService {

    int createNewTask(TaskDto taskDto);

    int markTaskAsCompleted(TaskDto taskDto);

    List<TaskDto> getAllTask(TaskDto taskDto);
}
