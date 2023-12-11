package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.TaskDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.entity.Task;
import com.taskmanagementsystem.taskmanagementsystem.exception.exceptions.NotFoundException;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.ITaskService;
import com.taskmanagementsystem.taskmanagementsystem.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final ModelMapper modelMapper;
    private final TaskRepo taskRepo;

    public PageResponse<TaskDTO> getAll(TaskDTO taskDto , Pageable pageable) {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Task exampleTask = new Task();
        exampleTask.setDescription(taskDto.getDescription());

        Example<Task> example = Example.of(exampleTask, customExampleMatcher);
        Page<Task> result = taskRepo.findAll(example, pageable);

        return new PageResponse<TaskDTO>(
                result.getContent().stream().map(this::convertToDto).toList(),
                result.getSize(),
                result.getNumber(),
                result.getTotalElements()
        );
    }

    public TaskDTO getTaskById(Long taskId) {
        return convertToDto(taskRepo.getTaskById(taskId).orElseThrow(() -> new NotFoundException("not found task")));
    }

    public TaskDTO addTask(TaskDTO taskDto) {
        Task task = convertToEntity(taskDto);

        return convertToDto(taskRepo.save(task));
    }

    public TaskDTO updateTask(TaskDTO taskDto){
        Task task = convertToEntity(taskDto);
        if(taskRepo.findById(task.getId()).isEmpty()){
            throw new NotFoundException("Cant find task with such id or you haven't passed any id");
        }

        return convertToDto(taskRepo.save(convertToEntity(taskDto)));
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new NotFoundException("Task with such id cant be found"));
        taskRepo.delete(task);
    }

    private Task convertToEntity(TaskDTO taskDto) throws ParseException {
        return modelMapper.map(taskDto, Task.class);
    }

    private TaskDTO convertToDto(Task task) throws ParseException {
        return modelMapper.map(task, TaskDTO.class);
    }
}
