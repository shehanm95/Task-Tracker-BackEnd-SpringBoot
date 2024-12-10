package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.mappers.SubTaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubTaskMapperImpl implements SubTaskMapper {

    private final ObjectMapper mapper;

    @Override
    public SubTask toEntity(SubTaskDto subTaskDto) {
        SubTask subTask = mapper.convertValue(subTaskDto, SubTask.class);
        return subTask;
    }

    @Override
    public SubTaskDto toDto(SubTask subTask) {
        SubTaskDto subTaskDto = mapper.convertValue(subTask,SubTaskDto.class);
        return subTaskDto;
    }


}
