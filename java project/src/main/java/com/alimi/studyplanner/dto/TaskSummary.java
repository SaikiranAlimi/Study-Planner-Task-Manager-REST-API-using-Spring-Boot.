package com.alimi.studyplanner.dto;

public record TaskSummary(long totalTasks, long completedTasks, long activeTasks, long overdueTasks) {
}
