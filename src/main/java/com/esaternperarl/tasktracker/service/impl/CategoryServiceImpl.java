package com.esaternperarl.tasktracker.service.impl;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.CategoryMapper;
import com.esaternperarl.tasktracker.repo.CategoryRepo;
import com.esaternperarl.tasktracker.repo.TaskRepo;
import com.esaternperarl.tasktracker.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Autowired
    @Lazy
    private TaskRepo taskRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public Category add(CategoryDto categoryDto) {
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

    /**
     * Deletes the requested category.
     * If any tasks belong to that category, they will be reassigned
     * to a category called "Others".
     *
     * @param id the unique identifier of the category to delete
     */
    @Override
    @Transactional
    public void delete(Long id) {
        categoryRepo.findById(id)
                .ifPresentOrElse(category -> {
                        //if this category has tasks
                        if(!category.getTaskList().isEmpty()){
                            // creating "others" category if not exist,
                            Category othersCategory = categoryRepo.findByName("Others")
                                    .orElse(
                                            categoryRepo.save(
                                                    new Category(null, "Others", Collections.emptyList())
                                            )
                                    );

                            // adding current tasks belongs to this category to "others" before deleting this,
                            List<Task> taskList = category.getTaskList();
                            taskList.forEach(task -> task.setCategory(othersCategory));

                            // and save the task list
                            taskRepo.saveAll(taskList);
                        }

                        // then delete this category
                        categoryRepo.delete(category);
                        },
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
    public Category findById(Long id) {
        return categoryRepo.findById(id)
        .orElseThrow(
                ()-> new IllegalArgumentException("requested category not in the database")
        );
    }
}
