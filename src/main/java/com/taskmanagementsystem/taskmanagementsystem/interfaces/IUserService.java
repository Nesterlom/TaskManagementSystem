package com.taskmanagementsystem.taskmanagementsystem.interfaces;

import com.taskmanagementsystem.taskmanagementsystem.dto.UserDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.request.RegisterRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface IUserService extends UserDetailsService {
    PageResponse<UserDTO> getAllUsers(UserDTO userDto, Pageable pageable);

    UserDTO findById(Long userId);

    User findByEmail(String email);

    void addUser(RegisterRequest registerRequest);

    UserDTO updateUser(UserDTO userDto, Principal principal);

    UserDTO updateUserEmail(UserDTO userDto, Principal principal);

    UserDTO updateUserPassword(UserDTO userDto, Principal principal);

    void deleteUser(Long userId);

    void deleteCurrentUser(Principal principal);
}
