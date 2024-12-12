package com.esaternperarl.tasktracker.controller;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.esaternperarl.tasktracker.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/all")
    public List<TaskDto> getAllTasks(){
        return taskService.findAll().stream().
                map(taskMapper::toDto)
                .toList();
    }

    @PostMapping("/add")
    public TaskDto addTask(@RequestBody @Valid TaskDto taskDto){
        return taskMapper.toDto(taskService.add(taskDto));
    }

    @PutMapping("/update")
    public TaskDto update(@RequestBody @Valid TaskDto taskDto){
        return taskMapper.toDto(taskService.update(taskDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id ){
        taskService.delete(id);
    }

    @GetMapping("/get/{id}")
    public TaskDto getTaskById(@PathVariable Long id){
        return taskMapper.toDto(taskService.getById(id));
    }

    @GetMapping("/finish/{id}")
    public TaskDto markAsFinished(@PathVariable Long id){
        return taskMapper.toDto(taskService.markAsFinished(id));
    }
}
