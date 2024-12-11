package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.mappers.SubTaskMapper;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubTaskMapperImpl implements SubTaskMapper {

    private final ObjectMapper mapper;
    @Autowired
    @Lazy
    private TaskMapper taskMapper;

    @Override
    public SubTask toEntity(SubTaskDto subTaskDto) {
        SubTask subTask = mapper.convertValue(subTaskDto, SubTask.class);
        subTask.setTask(taskMapper.toEntity(subTaskDto.getTaskDto()));
        return subTask;
    }

    @Override
    public SubTaskDto toDto(SubTask subTask) {
        SubTaskDto subTaskDto = mapper.convertValue(subTask,SubTaskDto.class);
        //subTaskDto.setTaskDto(taskMapper.toDto(subTask.getTask()));
        return subTaskDto;
    }


}
