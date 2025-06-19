package com.example.dailytracker.repository;

import com.example.dailytracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query method to find tasks by date
    List<Task> findByDate(LocalDate date);
}
