package com.alimi.studyplanner.repository;

import com.alimi.studyplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByDueDateAsc();
}
