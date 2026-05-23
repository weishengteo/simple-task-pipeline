package com.example.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@Mock
	TaskRepository taskRepository;
	
	@InjectMocks
	TaskService taskService;
	
	@Test
	void shouldReturnTaskById() {
		Task task = new Task(1L, "Test", "Test description", new Date());
		
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
		
		Task result = taskService.getById(1L);
		
		assertEquals(task, result);
		assertEquals("Test", result.getName());
		
	}
	
	@Test
	void shouldThrowException_whenTaskNotFound() {
		when(taskRepository.findById(1L)).thenReturn(Optional.empty());
		
		RuntimeException ex = assertThrows(
				RuntimeException.class,
				() -> taskService.getById(1L)
		);
		
		assertEquals("Task not found", ex.getMessage());
	}
	
	@Test
	void getAllTasksTest() {
		List<Task> tasks = List.of(
				new Task(1L, "A", "A desc", new Date()),
				new Task(2L, "B", "B desc", new Date())
		);
		
		when(taskRepository.findAll()).thenReturn(tasks);
		
		List<Task> result = taskService.getAllTasks();
		
		assertEquals(2, result.size());	
	}
	
	@Test
	void createTaskTest() {
		Task task = new Task(1L, "Test", "Test description", new Date());
		Task savedTask = new Task(1L, "Test", "Test description", new Date());
		
		when(taskRepository.save(task)).thenReturn(savedTask);
		
		Task result = taskService.createTask(task);
		
		assertEquals(1L, result.getId());
		assertEquals("Test", result.getName());
		
	}
	
}
