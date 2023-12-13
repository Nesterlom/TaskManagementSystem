package com.taskmanagementsystem.taskmanagementsystem.controller;

import com.taskmanagementsystem.taskmanagementsystem.dto.TaskDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
@Tag(name = "Tasks API")
public class TaskController {
    private final ITaskService taskService;

    @Operation(summary = "This endpoint allow us to paginate all tasks.Also you can use filtration and sorting")
    @GetMapping()
    public ResponseEntity<PageResponse<TaskDTO>> getAllTasks(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestBody(required = false) TaskDTO taskDto) {

        PageResponse<TaskDTO> filteredProducts = taskService.getAll(taskDto, pageable);
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }
    @Operation(summary = "This endpoint allow us to get information about one Task")
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @Operation(summary = "This endpoint allow us to add new Task")
    @PostMapping()
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDto, Principal principal){
        return ResponseEntity.ok(taskService.addTask(taskDto, principal));
    }

    @Operation(summary = "This endpoint allow us to update Task information")
    @PutMapping()
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDto){
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @Operation(summary = "This endpoint allow Admin to delete any Task")
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
}