package com.esaternperarl.tasktracker.service;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.entity.Task;

import java.util.List;
import java.util.UUID;

public interface SubTaskService {
    SubTask add(SubTaskDto subTaskDto);

    List<SubTask> findAll();

    Task update(SubTaskDto subTaskDto);

    void delete(Long id);
    //List<SubTask> getByTaskId(UUID );
}
