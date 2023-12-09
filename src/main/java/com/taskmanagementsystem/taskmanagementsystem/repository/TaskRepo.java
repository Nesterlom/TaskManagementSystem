package com.taskmanagementsystem.taskmanagementsystem.repository;

import com.taskmanagementsystem.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Long> {
    Optional<Task> getTaskById(Long id);
}
