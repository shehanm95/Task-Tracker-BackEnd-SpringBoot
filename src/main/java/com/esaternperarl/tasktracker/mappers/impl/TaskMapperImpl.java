package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.SubTaskMapper;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {

    private final ObjectMapper objectMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;

    @Override
    public Task toEntity(TaskDto taskDto) {
        Task entity = objectMapper.convertValue(taskDto,Task.class);

        return entity;
    }

    @Override
    public TaskDto toDto(Task task) {
        TaskDto dto = objectMapper.convertValue(task, TaskDto.class);
        dto.setSubTaskDtoList(task.getSubTaskList()
                .stream()
                .map(subTask -> subTaskMapper.toDto(subTask)).toList());
        return dto;
    }
}
