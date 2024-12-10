package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.mappers.CategoryMapper;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final TaskMapper taskMapper;
    private final ObjectMapper objectMapper;


    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category category = objectMapper.convertValue(categoryDto,Category.class);
        category.setTaskList(categoryDto.getTaskDtoList().stream().map(
                taskMapper::toEntity
        ).toList());
       return category;
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
