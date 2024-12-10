package com.esaternperarl.tasktracker.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Task> taskList;
}
