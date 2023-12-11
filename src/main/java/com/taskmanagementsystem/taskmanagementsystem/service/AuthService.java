package com.taskmanagementsystem.taskmanagementsystem.service;

import com.taskmanagementsystem.taskmanagementsystem.dto.request.AuthRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.request.RegisterRequest;
import com.taskmanagementsystem.taskmanagementsystem.dto.response.AuthResponse;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.IAuthService;
import com.taskmanagementsystem.taskmanagementsystem.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        return AuthResponse.builder()
                .token(jwtService.generateToken(userDetails))
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        userService.addUser(registerRequest);

        UserDetails userDetails = userService.loadUserByUsername(registerRequest.getEmail());
        return AuthResponse.builder()
                .token(jwtService.generateToken(userDetails))
                .build();
    }
}
