package com.esaternperarl.tasktracker.controller;

import com.esaternperarl.tasktracker.dto.CategoryDto;
import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.mappers.CategoryMapper;
import com.esaternperarl.tasktracker.repo.CategoryRepo;
import com.esaternperarl.tasktracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final  CategoryService categoryService;
    private  final CategoryMapper categoryMapper;

    @PostMapping("/add")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto){
        return categoryMapper.toDto(categoryService.save(categoryDto));
    }

   @PutMapping("/update")
    public CategoryDto updateCategory2(@RequestBody CategoryDto categoryDto){
        return categoryMapper.toDto(categoryService.update(categoryDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable UUID id){
        categoryService.delete(id);
    }

    @GetMapping("/get/{id}")
    public CategoryDto getCategory(@PathVariable UUID id){
        return categoryMapper.toDto(categoryService.findById(id));
    }

    @GetMapping("/all")
    public List<CategoryDto> getAllCategories(){
        return categoryService.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

}
