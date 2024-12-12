package com.esaternperarl.tasktracker.service;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> findAll();

    Task add(TaskDto taskDto);

    Task update(TaskDto taskDto);

    void delete(Long id);

    Task getById(Long id);

    Task taskFinishedRateSetter(Long id);

    Task markAsFinished(Long id);
}
