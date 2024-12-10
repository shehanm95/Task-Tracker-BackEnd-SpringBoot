package com.esaternperarl.tasktracker.controller;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ObjectMapper mapper;

    @GetMapping("/all")
    public List<TaskDto> getAllTasks(){
        return taskService.findAll().stream().
                map(task -> mapper
                .convertValue(task, TaskDto.class))
                .toList();
    }

    @PostMapping("/add")
    public TaskDto addTask(@RequestBody @Valid TaskDto taskDto){
        System.out.println(taskDto);
        return mapper.convertValue(taskService.add(taskDto) , TaskDto.class);
    }



}
