package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.model.ApiResponse;
import com.example.taskmanager.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/get")
	public ResponseEntity<ApiResponse<List<Task>>> getTasks() {
		return ResponseEntity.ok(ApiResponse.ok(taskService.getAllTasks()));
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody Task task) {
		return ResponseEntity.ok(ApiResponse.ok(taskService.createTask(task)));
	}
}
