package com.taskmanagementsystem.taskmanagementsystem.controller;

import com.taskmanagementsystem.taskmanagementsystem.dto.request.AuthRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.request.RegisterRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.AuthResponse;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication API")
public class AuthController {
    private final IAuthService authService;

    @Operation(summary = "Endpoint is used to authenticate already registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved token"),
            @ApiResponse(responseCode = "401", description = "Email or password wrong", content = {})
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @Operation(summary = "This endpoint allow new users to register new account")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
