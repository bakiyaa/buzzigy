package com.example.dailytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DailyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyTrackerApplication.class, args);
    }

}
