package com.example.dailytracker.service;

import com.example.dailytracker.model.Task;
import com.example.dailytracker.model.Task;
import com.example.dailytracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Cacheable(value = "allTasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Cacheable(value = "taskById", key = "#id")
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @CachePut(value = "taskById", key = "#result.id")
    @CacheEvict(value = {"allTasks", "tasksByDate"}, allEntries = true)
    @Override
    public Task createTask(Task task) {
        // Add any business logic before saving, e.g., validation
        return taskRepository.save(task);
    }

    @CachePut(value = "taskById", key = "#id", condition = "#result.present")
    @CacheEvict(value = {"allTasks", "tasksByDate"}, allEntries = true)
    @Override
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setStartTime(taskDetails.getStartTime());
            existingTask.setEndTime(taskDetails.getEndTime());
            existingTask.setDate(taskDetails.getDate());
            // Add any other fields to update
            return taskRepository.save(existingTask);
        });
    }

    @CacheEvict(value = "taskById", key = "#id")
    @CacheEvict(value = {"allTasks", "tasksByDate"}, allEntries = true)
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "tasksByDate", key = "#date.toString()")
    public List<Task> findTasksByDate(LocalDate date) {
        return taskRepository.findByDate(date);
    }
}
