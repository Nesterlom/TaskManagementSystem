package com.taskmanagementsystem.taskmanagementsystem.dto;

import com.taskmanagementsystem.taskmanagementsystem.entity.Role;
import com.taskmanagementsystem.taskmanagementsystem.entity.Task;
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
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private List<Task> tasks = new ArrayList<>();
}
