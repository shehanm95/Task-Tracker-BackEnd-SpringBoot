package com.esaternperarl.tasktracker.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        List<TaskDto> taskDtoList
) {

}
