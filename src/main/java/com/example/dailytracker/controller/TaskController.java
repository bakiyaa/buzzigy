package com.example.dailytracker.controller;

import com.example.dailytracker.model.Task;
import com.example.dailytracker.service.TaskService;
import com.example.dailytracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id)));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        taskService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id + " for update."));
        Task updatedTask = taskService.updateTask(id, taskDetails)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id + " after attempting update. Should not happen if first check passed.")); // Should ideally not happen if first check passes
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date/{date}")
    public List<Task> getTasksByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taskService.findTasksByDate(date);
    }
}
