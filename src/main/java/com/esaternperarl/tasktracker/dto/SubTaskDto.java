package com.esaternperarl.tasktracker.dto;


import java.util.UUID;


public record SubTaskDto(
        UUID id,
        String subTaskName,
        Boolean isFinished,
        TaskDto taskDto
) {}