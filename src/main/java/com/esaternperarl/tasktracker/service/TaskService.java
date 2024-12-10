package com.esaternperarl.tasktracker.service;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task add(TaskDto taskDto);
}
