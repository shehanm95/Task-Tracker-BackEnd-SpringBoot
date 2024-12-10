package com.esaternperarl.tasktracker.controller;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/all")
    public List<TaskDto> getAllTasks(){
        return taskService.findAll().stream().
                map(task -> mapper
                .convertValue(task, TaskDto.class))
                .toList();
    }
}
