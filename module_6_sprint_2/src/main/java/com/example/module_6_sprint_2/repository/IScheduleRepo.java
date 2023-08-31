package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScheduleRepo extends JpaRepository<Schedule,Integer> {
}
