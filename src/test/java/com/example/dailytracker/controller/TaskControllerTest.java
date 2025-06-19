package com.example.dailytracker.controller;

import com.example.dailytracker.model.Task;
import com.example.dailytracker.service.TaskService;
import com.example.dailytracker.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // Ensure this is available

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;


@WebMvcTest(TaskController.class) // Test only the TaskController layer
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Spring Boot's mock bean for the service layer
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper; // Autowired ObjectMapper

    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        // Register JavaTimeModule if not registered globally (often needed for Spring Boot tests with Jackson)
        objectMapper.registerModule(new JavaTimeModule());

        task1 = new Task("Controller Test Task 1", LocalDateTime.of(2023,1,1,10,0), LocalDateTime.of(2023,1,1,11,0), LocalDate.of(2023,1,1));
        task1.setId(1L);
        task2 = new Task("Controller Test Task 2", LocalDateTime.of(2023,1,2,12,0), LocalDateTime.of(2023,1,2,13,0), LocalDate.of(2023,1,2));
        task2.setId(2L);
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() throws Exception {
        List<Task> allTasks = Arrays.asList(task1, task2);
        when(taskService.getAllTasks()).thenReturn(allTasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description", is("Controller Test Task 1")))
                .andExpect(jsonPath("$[1].description", is("Controller Test Task 2")));
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getTaskById_WhenTaskExists_ShouldReturnTask() throws Exception {
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task1));

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Controller Test Task 1")));
        verify(taskService, times(1)).getTaskById(1L);
    }

    @Test
    void getTaskById_WhenTaskDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(taskService.getTaskById(3L)).thenThrow(new ResourceNotFoundException("Task not found with id 3"));

        mockMvc.perform(get("/api/tasks/3"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Task not found with id 3")));
        verify(taskService, times(1)).getTaskById(3L);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() throws Exception {
        when(taskService.createTask(any(Task.class))).thenReturn(task1);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is("Controller Test Task 1")));
        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    void updateTask_WhenTaskExists_ShouldReturnUpdatedTask() throws Exception {
        Task updatedDetails = new Task("Updated Task", task1.getStartTime(), task1.getEndTime(), task1.getDate());
        updatedDetails.setId(1L);

        // First, mock the getTaskById call made by the controller before update
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task1));
        // Then, mock the updateTask call
        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(Optional.of(updatedDetails));


        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Updated Task")));
        verify(taskService, times(1)).getTaskById(1L); // Called by controller for check
        verify(taskService, times(1)).updateTask(eq(1L), any(Task.class));
    }

    @Test
    void updateTask_WhenTaskDoesNotExist_ShouldReturnNotFound() throws Exception {
        Task taskDetails = new Task("Non Existent", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now());
        // Mock the getTaskById to throw ResourceNotFoundException, as per controller's logic
        when(taskService.getTaskById(3L)).thenThrow(new ResourceNotFoundException("Task not found with id 3 for update."));

        mockMvc.perform(put("/api/tasks/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDetails)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Task not found with id 3 for update.")));
        verify(taskService, times(1)).getTaskById(3L); // Controller checks this first
        verify(taskService, never()).updateTask(eq(3L), any(Task.class)); // updateTask should not be called
    }

    @Test
    void deleteTask_WhenTaskExists_ShouldReturnNoContent() throws Exception {
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task1)); // Mocking for the check in controller
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
        verify(taskService, times(1)).getTaskById(1L);
        verify(taskService, times(1)).deleteTask(1L);
    }

    @Test
    void deleteTask_WhenTaskDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(taskService.getTaskById(3L)).thenThrow(new ResourceNotFoundException("Task not found with id 3"));

        mockMvc.perform(delete("/api/tasks/3"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Task not found with id 3")));
        verify(taskService, times(1)).getTaskById(3L);
        verify(taskService, never()).deleteTask(3L);
    }

    @Test
    void getTasksByDate_ShouldReturnListOfTasks() throws Exception {
        LocalDate dateToFind = LocalDate.of(2023, 1, 1);
        List<Task> tasksOnDate = Arrays.asList(task1);
        when(taskService.findTasksByDate(dateToFind)).thenReturn(tasksOnDate);

        mockMvc.perform(get("/api/tasks/date/" + dateToFind.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is("Controller Test Task 1")));
        verify(taskService, times(1)).findTasksByDate(dateToFind);
    }
}
