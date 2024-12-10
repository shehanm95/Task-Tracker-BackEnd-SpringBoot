package com.esaternperarl.tasktracker.dto;

import com.esaternperarl.tasktracker.entity.Category;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String topic,
        String description,
        Category category,
        List<SubTaskDto> subTaskDtoList,
        LocalDate startingDate,
        LocalDate dueDate,
        Double finishingRate,
        Boolean isFinished
) {}

