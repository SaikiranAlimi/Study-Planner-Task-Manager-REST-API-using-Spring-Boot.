package com.alimi.studyplanner.dto;

import com.alimi.studyplanner.model.Priority;
import com.alimi.studyplanner.model.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 120, message = "Title must be at most 120 characters")
        String title,

        @NotBlank(message = "Subject is required")
        @Size(max = 80, message = "Subject must be at most 80 characters")
        String subject,

        @Size(max = 500, message = "Description must be at most 500 characters")
        String description,

        @NotNull(message = "Priority is required")
        Priority priority,

        TaskStatus status,

        @NotNull(message = "Due date is required")
        @FutureOrPresent(message = "Due date cannot be in the past")
        LocalDate dueDate
) {
}
