package com.esaternperarl.tasktracker.service;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> findAll();

    Task add(TaskDto taskDto);

    Task update(TaskDto taskDto);

    void delete(UUID id);

    Task getById(UUID id);

    Task taskFinishedRateSetter(UUID id);

    Task markAsFinished(UUID id);
}
