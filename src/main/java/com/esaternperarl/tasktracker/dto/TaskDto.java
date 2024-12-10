package com.esaternperarl.tasktracker.dto;

import com.esaternperarl.tasktracker.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {

    private UUID id;
    @NonNull
    @NotEmpty
    private String topic;
    private String description;
    @NonNull
    private Category category;
    private List<SubTaskDto> subTaskDtoList;
    private LocalDate startingDate;
    private LocalDate dueDate;
    private Double finishingRate;
    private Boolean isFinished;
}

