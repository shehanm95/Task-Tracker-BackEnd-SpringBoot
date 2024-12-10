package com.esaternperarl.tasktracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //@Column(nullable = false , unique = true)
    private String topic;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "task")
    //@JsonIgnore
    private List<SubTask> subTaskList;
    private LocalDate startingDate;
    private LocalDate dueDate;
    private Double finishingRate;
    private Boolean isFinished;
}
