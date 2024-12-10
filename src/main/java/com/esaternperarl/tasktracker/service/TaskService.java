package com.esaternperarl.tasktracker.service;

import com.esaternperarl.tasktracker.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
}
