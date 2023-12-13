package com.taskmanagementsystem.taskmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Task title can't be empty")
    @Size(min = 1, max = 20, message = "Size of task title should be from 1 to 20 characters")
    private String title;

    @NotNull(message = "Task description can't be null")
    @Size(min = 1, max = 100, message = "Size of task description should be from 1 to 100 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private String author;//email?

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany
    private List<User> workers;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();
}
