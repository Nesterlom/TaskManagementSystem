package com.taskmanagementsystem.taskmanagementsystem.controller;

import com.taskmanagementsystem.taskmanagementsystem.dto.TaskDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final ITaskService taskService;

    @GetMapping()
    public ResponseEntity<PageResponse<TaskDTO>> getAllTasks(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestBody TaskDTO taskDto) {

        PageResponse<TaskDTO> filteredProducts = taskService.getAll(taskDto, pageable);
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDto){
        return ResponseEntity.ok(taskService.addTask(taskDto));
    }

    @PutMapping()
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDto){
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}