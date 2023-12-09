package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.TaskDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ITaskService {

    PageResponse<TaskDTO> getAll(TaskDTO taskDto, Pageable pageable);

    TaskDTO getTaskById(Long taskId);

    TaskDTO addTask(TaskDTO taskDto);

    TaskDTO updateTask(TaskDTO taskDto);

    void deleteTask(Long taskId);
}
