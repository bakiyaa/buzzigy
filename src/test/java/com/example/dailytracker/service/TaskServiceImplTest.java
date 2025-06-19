package com.example.dailytracker.service;

import com.example.dailytracker.model.Task;
import com.example.dailytracker.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        task1 = new Task("Test Task 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1), LocalDate.now());
        task1.setId(1L);
        task2 = new Task("Test Task 2", LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1).plusHours(2), LocalDate.now().minusDays(1));
        task2.setId(2L);
    }

    @Test
    void getAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getTaskById_Found() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        Optional<Task> task = taskService.getTaskById(1L);
        assertTrue(task.isPresent());
        assertEquals("Test Task 1", task.get().getDescription());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getTaskById_NotFound() {
        when(taskRepository.findById(3L)).thenReturn(Optional.empty());
        Optional<Task> task = taskService.getTaskById(3L);
        assertFalse(task.isPresent());
        verify(taskRepository, times(1)).findById(3L);
    }

    @Test
    void createTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task1);
        Task createdTask = taskService.createTask(task1);
        assertNotNull(createdTask);
        assertEquals("Test Task 1", createdTask.getDescription());
        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    void updateTask_Found() {
        Task updatedDetails = new Task("Updated Task 1", task1.getStartTime(), task1.getEndTime(), task1.getDate());
        updatedDetails.setId(1L); // Ensure ID is set for the "existing" task mock

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedDetails); // mock save to return the updated task

        Optional<Task> result = taskService.updateTask(1L, updatedDetails);

        assertTrue(result.isPresent());
        assertEquals("Updated Task 1", result.get().getDescription());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void updateTask_NotFound() {
        Task updatedDetails = new Task("Non Existent Task", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now());
        when(taskRepository.findById(3L)).thenReturn(Optional.empty());
        Optional<Task> result = taskService.updateTask(3L, updatedDetails);
        assertFalse(result.isPresent());
        verify(taskRepository, times(1)).findById(3L);
        verify(taskRepository, never()).save(any(Task.class));
    }


    @Test
    void deleteTask_Found() {
        // No need to mock findById for deleteById in service as it's not checked there before calling delete.
        // If service layer had a check, we'd mock findById.
        // Controller layer does the check.
        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void findTasksByDate() {
        LocalDate dateToFind = LocalDate.now();
        when(taskRepository.findByDate(dateToFind)).thenReturn(Arrays.asList(task1));
        List<Task> tasks = taskService.findTasksByDate(dateToFind);
        assertEquals(1, tasks.size());
        assertEquals(dateToFind, tasks.get(0).getDate());
        verify(taskRepository, times(1)).findByDate(dateToFind);
    }
}
