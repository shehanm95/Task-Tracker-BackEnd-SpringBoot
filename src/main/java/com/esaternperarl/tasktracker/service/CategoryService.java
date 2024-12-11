package com.esaternperarl.tasktracker.service;


import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category add(CategoryDto categoryDto);
    Category update(CategoryDto categoryDto);

    void delete(UUID id);

    List<Category> findAll();

    Category findById(UUID id);
}
