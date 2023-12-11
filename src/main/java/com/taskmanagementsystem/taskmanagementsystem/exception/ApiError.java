package com.taskmanagementsystem.taskmanagementsystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ApiError {
    private final String message;
    private final HttpStatus httpStatus;
    private List<String> errors = new ArrayList<>();
    private Date timestamp;

    public ApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = new Date();
    }

    public ApiError(String message, HttpStatus httpStatus, List<String> errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.timestamp = new Date();
    }
}
