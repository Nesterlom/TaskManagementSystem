package com.taskmanagementsystem.taskmanagementsystem.interfaces;

import com.taskmanagementsystem.taskmanagementsystem.dto.TaskDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface ITaskService {

    PageResponse<TaskDTO> getAll(TaskDTO taskDto, Pageable pageable);

    TaskDTO getTaskById(Long taskId);

    TaskDTO addTask(TaskDTO taskDto, Principal principal);

    TaskDTO updateTask(TaskDTO taskDto);

    void deleteTask(Long taskId);
}
