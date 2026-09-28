package com.alimi.studyplanner.dto;

import com.alimi.studyplanner.model.Priority;
import com.alimi.studyplanner.model.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String subject,
        String description,
        Priority priority,
        TaskStatus status,
        LocalDate dueDate,
        LocalDateTime createdAt
) {
}
