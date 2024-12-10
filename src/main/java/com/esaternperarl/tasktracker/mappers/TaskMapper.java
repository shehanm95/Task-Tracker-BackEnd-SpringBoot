package com.esaternperarl.tasktracker.mappers;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface TaskMapper {

    public Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);
}
