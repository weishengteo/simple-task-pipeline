package com.example.taskmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;



@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockitoBean
	TaskService taskService;
	
	@Test
	void shouldGetAllTasks() throws Exception {
		List<Task> tasks = List.of(
				new Task(1L, "A", "A desc", new Date()),
				new Task(2L, "B", "B desc", new Date())
		);
		
		when(taskService.getAllTasks()).thenReturn(tasks);
		
		mockMvc.perform(get("/task/get"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.length()").value(2))
			.andExpect(jsonPath("$.data[0].name").value("A"));
	}
	
	@Test
	void shouldReturnError_whenServiceThrowsException() throws Exception {

	    when(taskService.getAllTasks())
	            .thenThrow(new RuntimeException("Database error"));

	    mockMvc.perform(get("/task/get"))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.message").value("Database error"));
	}
}
