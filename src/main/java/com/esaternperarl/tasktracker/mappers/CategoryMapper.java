package com.esaternperarl.tasktracker.mappers;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import org.springframework.stereotype.Component;

@Component
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category categoryEntity);
}
