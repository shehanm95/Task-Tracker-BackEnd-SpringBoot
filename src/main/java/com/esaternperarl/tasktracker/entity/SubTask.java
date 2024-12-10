package com.esaternperarl.tasktracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sub_task")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String subTaskName;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    private Boolean isFinished;
}
