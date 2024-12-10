package com.esaternperarl.tasktracker.mappers;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;

public interface SubTaskMapper {
    SubTask toEntity(SubTaskDto subTaskDto);
    SubTaskDto toDto (SubTask subTask);
}
