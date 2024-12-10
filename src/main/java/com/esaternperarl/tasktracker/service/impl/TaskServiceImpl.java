package com.esaternperarl.tasktracker.service.impl;

import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.repo.TaskRepo;
import com.esaternperarl.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> findAll() {
        return taskRepo.findAll();
    }
}
