package com.example.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.taskmanager.model.ApiResponse;
import com.example.taskmanager.util.Status;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ApiResponse.error(Status.NOT_FOUND, e.getMessage()));
	}
}
