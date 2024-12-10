package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.mappers.CategoryMapper;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    private final TaskMapper taskMapper;

    public CategoryMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
       return new Category(
               categoryDto.id(),
               categoryDto.name(),
               Optional.ofNullable(categoryDto.taskDtoList()).map(
                      tasks -> tasks.stream().map(taskMapper::toEntity).toList()
               ).orElse(Collections.emptyList())
       );
    }

    @Override
    public CategoryDto toDto(Category categoryEntity) {
        return new CategoryDto(
                categoryEntity.getId(),
                categoryEntity.getName(),
                Optional.ofNullable(categoryEntity.getTaskList()).map(
                        tasks -> tasks.stream().map(taskMapper::toDto).toList()
                ).orElse(Collections.emptyList())
        );
    }
}
