package com.esaternperarl.tasktracker.service.impl;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.esaternperarl.tasktracker.repo.TaskRepo;
import com.esaternperarl.tasktracker.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;


    @Override
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @Override
    public Task add(TaskDto taskDto) {

        return taskRepo.save(taskMapper.toEntity(taskDto));
    }
}
