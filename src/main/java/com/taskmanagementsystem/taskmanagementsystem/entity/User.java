package com.taskmanagementsystem.taskmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User email shouldn't be null")
    @Size(min = 1, max = 30, message = "Size of user email should be from 1 to 30 characters")
    @Email(message = "Wrong email")
    private String email;

    @NotNull(message = "User Password shouldn't be null")
    @Size(min = 5, message = "Size of user password should be from 5 to 30 characters")
    private String password;

    @Size(min = 1, max = 20, message = "User name and surname should be from 1 to 20 characters")
    private String name;

    @Size(min = 1, max = 20, message = "User name and surname should be from 1 to 20 characters")
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Task> tasks = new ArrayList<>();
}
