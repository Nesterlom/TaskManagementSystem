package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.UserDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface IUserService {
    PageResponse<UserDTO> getAllUsers(UserDTO userDto, Pageable pageable);

    UserDTO findById(Long userId);

    UserDTO updateUser(UserDTO userDto, Principal principal);

    UserDTO updateUserEmail(UserDTO userDto, Principal principal);

    UserDTO updateUserPassword(UserDTO userDto, Principal principal);

    void deleteUser(Long userId);

    void deleteCurrentUser(Principal principal);
}
