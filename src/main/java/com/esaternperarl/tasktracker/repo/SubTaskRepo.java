package com.esaternperarl.tasktracker.repo;

import com.esaternperarl.tasktracker.entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubTaskRepo extends JpaRepository<SubTask, Long> {
}

