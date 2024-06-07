package com.project.task.dao;

import com.project.task.dto.TaskDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskDao {

    int createNewTask(TaskDto taskDto);

    int markTaskAsCompleted(TaskDto taskDto);

    List<TaskDto> getAllTask(TaskDto taskDto);
}
