package com.esaternperarl.tasktracker.mappers.impl;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.CategoryMapper;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.CollectionReadOnlyAccess;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final TaskMapper taskMapper;
    private final ObjectMapper objectMapper;


    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category category = objectMapper.convertValue(categoryDto, Category.class);

        List<Task> tasks = Optional.ofNullable(categoryDto.getTaskDtoList())
              .orElse(Collections.emptyList())
              .stream()
              .map(taskMapper::toEntity).toList();
        
        category.setTaskList(tasks);
        return category;
    }


    @Override
    public CategoryDto toDto(Category categoryEntity) {
        return new CategoryDto(
                categoryEntity.getId(),
                categoryEntity.getName(),
               // Collections.emptyList()
                Optional.ofNullable(categoryEntity.getTaskList()).map(
                        tasks -> tasks.stream().map(taskMapper::toDto).toList()
                ).orElse(Collections.emptyList())
       );
   }
}
