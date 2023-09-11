package com.example.module_6_sprint_2.service;

import com.example.module_6_sprint_2.model.Schedule;

import java.util.Set;

public interface IScheduleService {
    Schedule findByDateDepartureAndTimeDeparture(String date, String time);
    Set<String> findByDateDeparture();
    Set<String> findByTimeDeparture();
}
