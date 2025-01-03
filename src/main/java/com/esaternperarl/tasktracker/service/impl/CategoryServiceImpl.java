package com.esaternperarl.tasktracker.service.impl;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.mappers.CategoryMapper;
import com.esaternperarl.tasktracker.repo.CategoryRepo;
import com.esaternperarl.tasktracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public Category save(CategoryDto categoryDto) {
        return categoryRepo.save(categoryMapper.toEntity(categoryDto));
    }

    @Override
    public Category update(CategoryDto categoryDto) {

        if(categoryDto.getId() == null) throw new IllegalArgumentException("Category Id Must Exist to Update a Category");
        else if(!categoryRepo.existsById(categoryDto.getId())) throw new IllegalArgumentException("Category with this Id '"+categoryDto.getId()+"' must exist to update a Category, Category 'Not Exist' in the database");

        // Retrieve the category and update
        return categoryRepo.findById(categoryDto.getId())
                .map(category -> {
                    category.setName(categoryDto.getName());
                    return categoryRepo.save(category);
                })
                .orElseThrow(() -> new IllegalStateException("Unexpected error: Category with ID '" + categoryDto.getId() + "' not found"));


    }

    @Override
    public void delete(UUID id) {
        categoryRepo.findById(id)
                .ifPresentOrElse(
                        categoryRepo::delete,
                        () -> {
                            throw new IllegalArgumentException("Category with ID '" + id + "' does not exist in the database");
                        }
                );
    }
    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepo.findById(id)
        .orElseThrow(
                ()-> new IllegalArgumentException("requested category not in the database")
        );
    }
}
