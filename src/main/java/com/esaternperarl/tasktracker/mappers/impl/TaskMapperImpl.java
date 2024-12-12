package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.SubTaskMapper;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {

    private final ObjectMapper objectMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;

    @Override
    public Task toEntity(TaskDto taskDto) {
        Task entity = objectMapper.convertValue(taskDto,Task.class);
        List<SubTask> subTaskList= Optional.ofNullable(taskDto.getSubTaskDtoList())
                .orElse(Collections.emptyList())
                .stream().map(subTaskMapper::toEntity).toList();

        entity.setSubTaskList(subTaskList);
        return entity;
    }

    @Override
    public TaskDto toDto(Task task) {
        TaskDto dto = objectMapper.convertValue(task, TaskDto.class);
        List<SubTaskDto> subTaskDtoList = Optional.ofNullable(task.getSubTaskList())
                .orElse(Collections.emptyList())
                .stream()
                .map(subTask -> subTaskMapper.toDto(subTask)).toList();
        dto.setSubTaskDtoList(subTaskDtoList);
        return dto;
    }
}
