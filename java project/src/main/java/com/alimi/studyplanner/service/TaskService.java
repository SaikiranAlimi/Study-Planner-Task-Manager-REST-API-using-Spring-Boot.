package com.alimi.studyplanner.service;

import com.alimi.studyplanner.dto.TaskRequest;
import com.alimi.studyplanner.dto.TaskResponse;
import com.alimi.studyplanner.dto.TaskSummary;
import com.alimi.studyplanner.exception.TaskNotFoundException;
import com.alimi.studyplanner.model.Priority;
import com.alimi.studyplanner.model.Task;
import com.alimi.studyplanner.model.TaskStatus;
import com.alimi.studyplanner.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task(
                request.title().trim(),
                request.subject().trim(),
                cleanDescription(request.description()),
                request.priority(),
                request.status() == null ? TaskStatus.TODO : request.status(),
                request.dueDate()
        );
        return toResponse(taskRepository.save(task));
    }

    public List<TaskResponse> getTasks(TaskStatus status, Priority priority, String subject) {
        String normalizedSubject = subject == null ? "" : subject.trim().toLowerCase(Locale.ROOT);

        return taskRepository.findAllByOrderByDueDateAsc().stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> priority == null || task.getPriority() == priority)
                .filter(task -> normalizedSubject.isEmpty()
                        || task.getSubject().toLowerCase(Locale.ROOT).contains(normalizedSubject))
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse getTask(Long id) {
        return toResponse(findTask(id));
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = findTask(id);
        task.setTitle(request.title().trim());
        task.setSubject(request.subject().trim());
        task.setDescription(cleanDescription(request.description()));
        task.setPriority(request.priority());
        task.setStatus(request.status() == null ? task.getStatus() : request.status());
        task.setDueDate(request.dueDate());

        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse completeTask(Long id) {
        Task task = findTask(id);
        task.setStatus(TaskStatus.COMPLETED);
        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.delete(findTask(id));
    }

    public TaskSummary getSummary() {
        List<Task> tasks = taskRepository.findAll();
        long completed = tasks.stream().filter(task -> task.getStatus() == TaskStatus.COMPLETED).count();
        long overdue = tasks.stream()
                .filter(task -> task.getStatus() != TaskStatus.COMPLETED)
                .filter(task -> task.getDueDate().isBefore(LocalDate.now()))
                .count();

        return new TaskSummary(tasks.size(), completed, tasks.size() - completed, overdue);
    }

    private Task findTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    private String cleanDescription(String description) {
        return description == null || description.isBlank() ? null : description.trim();
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getSubject(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getDueDate(),
                task.getCreatedAt()
        );
    }
}
