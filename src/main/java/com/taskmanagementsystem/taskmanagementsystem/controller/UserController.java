package com.taskmanagementsystem.taskmanagementsystem.controller;

import com.taskmanagementsystem.taskmanagementsystem.dto.CommentDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.UserDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @GetMapping("/test")
    public String test(){
        return "Works";
    }

    @GetMapping()
    public ResponseEntity<PageResponse<UserDTO>> getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestBody UserDTO userDto) {

        PageResponse<UserDTO> filteredProducts = userService.getAllUsers(userDto, pageable);
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto, Principal principal){
        return ResponseEntity.ok(userService.updateUser(userDto, principal));
    }

    @PutMapping("/email")
    public ResponseEntity<UserDTO> updateUserEmail(@RequestBody UserDTO userDto, Principal principal){
        return ResponseEntity.ok(userService.updateUserEmail(userDto, principal));
    }

    @PutMapping("/password")
    public ResponseEntity<UserDTO> updateUserPassword(@RequestBody UserDTO userDto, Principal principal){
        return ResponseEntity.ok(userService.updateUserPassword(userDto, principal));
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteCurrentUser(Principal principal){
        userService.deleteCurrentUser(principal);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
