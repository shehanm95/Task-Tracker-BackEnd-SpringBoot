package com.esaternperarl.tasktracker.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus status,
        String message,
        String details
) {
}
