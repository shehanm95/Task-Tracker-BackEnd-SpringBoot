package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task toEntity(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto toDto(Task task) {
        return null;
    }
}
