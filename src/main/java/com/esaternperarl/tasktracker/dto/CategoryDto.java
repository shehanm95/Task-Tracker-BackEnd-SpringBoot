package com.esaternperarl.tasktracker.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto{
    private UUID id;
    @NonNull
    @NotEmpty
    private String name;
    private List<TaskDto> taskDtoList;
}
