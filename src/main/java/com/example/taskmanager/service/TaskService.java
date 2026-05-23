package com.example.taskmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public Task getById(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Task not found"));
	}
	
	public Task createTask(Task task) {
		task.setCreateDate(new Date());
		return taskRepository.save(task);
	}
}
