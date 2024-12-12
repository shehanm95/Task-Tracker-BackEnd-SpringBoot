package com.esaternperarl.tasktracker.repo;

import com.esaternperarl.tasktracker.entity.Category;
import com.esaternperarl.tasktracker.service.impl.CategoryServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
