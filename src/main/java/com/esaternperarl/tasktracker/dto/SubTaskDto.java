package com.esaternperarl.tasktracker.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubTaskDto {
    private UUID id;
    @NonNull
    @NotEmpty
    private String subTaskName;
    private Boolean isFinished;
    private TaskDto taskDto;
}