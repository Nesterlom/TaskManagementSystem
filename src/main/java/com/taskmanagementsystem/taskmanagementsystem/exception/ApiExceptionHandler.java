package com.taskmanagementsystem.taskmanagementsystem.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException e){
        var status = HttpStatus.UNAUTHORIZED;
        ApiError a = new ApiError(
                e.getMessage(),
                status
        );

        return new ResponseEntity<>(a, status);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e){
        var status = HttpStatus.BAD_REQUEST;
        ApiError a = new ApiError(
                e.getMessage(),
                status
        );

        return new ResponseEntity<>(a, status);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException e){
        var status = HttpStatus.BAD_REQUEST;

        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String propertyName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.add(propertyName + ": " + errorMessage);
        }

        ApiError a = new ApiError(
                "Wrong params",
                status,
                errors
        );

        return new ResponseEntity<>(a, status);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ApiError> handleExpiredJwtException(ExpiredJwtException e){
        var status = HttpStatus.UNAUTHORIZED;

        ApiError a = new ApiError(
                "JWT was expired",
                status
        );

        return new ResponseEntity<>(a, status);
    }

    @ExceptionHandler(value = {SignatureException.class})
    public ResponseEntity<ApiError> handleExpiredJwtException(SignatureException e){
        var status = HttpStatus.UNAUTHORIZED;

        ApiError a = new ApiError(
                e.getMessage(),
                status
        );

        return new ResponseEntity<>(a, status);
    }
}
