package com.example.dailytracker.service;

import com.example.dailytracker.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Task createTask(Task task);
    Optional<Task> updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
    List<Task> findTasksByDate(LocalDate date);
}
