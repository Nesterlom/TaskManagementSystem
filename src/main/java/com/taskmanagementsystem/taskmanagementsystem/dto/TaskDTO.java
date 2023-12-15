package com.taskmanagementsystem.taskmanagementsystem.dto;

import com.taskmanagementsystem.taskmanagementsystem.entity.Comment;
import com.taskmanagementsystem.taskmanagementsystem.entity.Priority;
import com.taskmanagementsystem.taskmanagementsystem.entity.Status;
import com.taskmanagementsystem.taskmanagementsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String author;
    private Priority priority;
    private Status status;
    private List<User> workers = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
}
