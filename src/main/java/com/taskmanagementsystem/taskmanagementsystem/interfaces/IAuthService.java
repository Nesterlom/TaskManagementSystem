package com.taskmanagementsystem.taskmanagementsystem.interfaces;

import com.taskmanagementsystem.taskmanagementsystem.dto.request.AuthRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.request.RegisterRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.AuthResponse;

public interface IAuthService {
    AuthResponse authenticate(AuthRequest authRequest);

    AuthResponse register(RegisterRequest registerRequest);
}
