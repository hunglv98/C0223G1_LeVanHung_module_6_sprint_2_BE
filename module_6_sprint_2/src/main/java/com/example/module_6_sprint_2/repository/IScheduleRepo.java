package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IScheduleRepo extends JpaRepository<Schedule,Integer> {
    Schedule findByDateDepartureAndTimeDeparture(String date, String time);
    Set<Schedule> findByDateDeparture(String date);
    Set<Schedule> findByTimeDeparture(String time);

    List<Schedule> findAll();

    Schedule getSchedulesByIdSchedule(int id);
}
