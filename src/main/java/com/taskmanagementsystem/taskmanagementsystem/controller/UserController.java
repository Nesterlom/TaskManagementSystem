package com.taskmanagementsystem.taskmanagementsystem.controller;

import com.taskmanagementsystem.taskmanagementsystem.dto.CommentDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.UserDTO;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.PageResponse;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.IUserService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "Users API")
public class UserController {
    private final IUserService userService;

    @GetMapping("/test")
    @Operation(summary = "This is a TEST endpoint")
    public String test(){
        return "Works";
    }

    @Operation(summary = "This endpoint allow us to paginate all users.Also you can use filtration and sorting")
    @GetMapping()
    public ResponseEntity<PageResponse<UserDTO>> getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestBody(required = false) UserDTO userDto) {

        PageResponse<UserDTO> filteredProducts = userService.getAllUsers(userDto, pageable);
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }

    @Operation(summary = "This endpoint allow us to get information about one User")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @Operation(summary = "This endpoint allow us to update user information, but if you want to change password and email you should use another endpoints")
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto, Principal principal){
        return ResponseEntity.ok(userService.updateUser(userDto, principal));
    }

    @Operation(summary = "This endpoint allow us to change user email")
    @PutMapping("/email")
    public ResponseEntity<UserDTO> updateUserEmail(@RequestBody UserDTO userDto, Principal principal){
        return ResponseEntity.ok(userService.updateUserEmail(userDto, principal));
    }

    @Operation(summary = "This endpoint allow us to change user password")
    @PutMapping("/password")
    public ResponseEntity<UserDTO> updateUserPassword(@RequestBody UserDTO userDto, Principal principal){
        return ResponseEntity.ok(userService.updateUserPassword(userDto, principal));
    }

    @Operation(summary = "This endpoint is used to delete used that is logged in now")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteCurrentUser(Principal principal){
        userService.deleteCurrentUser(principal);
    }

    @Operation(summary = "This endpoint allow Admin to delete any user")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
