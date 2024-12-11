package com.esaternperarl.tasktracker.service;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;

import java.util.List;
import java.util.UUID;

public interface SubTaskService {
    SubTask add(SubTaskDto subTaskDto);

    List<SubTask> findAll();

    SubTask update(SubTaskDto subTaskDto);

    void delete(UUID id);
    //List<SubTask> getByTaskId(UUID );
}
