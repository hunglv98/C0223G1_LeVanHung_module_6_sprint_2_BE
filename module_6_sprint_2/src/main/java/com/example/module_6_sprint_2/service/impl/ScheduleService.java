package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Schedule;
import com.example.module_6_sprint_2.repository.IScheduleRepo;
import com.example.module_6_sprint_2.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private IScheduleRepo scheduleRepo;
    @Override
    public Schedule findByDateDepartureAndTimeDeparture(String date, String time) {
        return scheduleRepo.findByDateDepartureAndTimeDeparture(date,time);
    }

    @Override
    public Set<String> findByDateDeparture() {
        Set<String> set = new LinkedHashSet<>();
        List<Schedule> list = scheduleRepo.findAll();
        for (Schedule s: list) {
            set.add(s.getDateDeparture());
        }
        return set;
    }

    @Override
    public Set<String> findByTimeDeparture() {
        Set<String> set = new LinkedHashSet<>();
        List<Schedule> list = scheduleRepo.findAll();
        for (Schedule s: list) {
            set.add(s.getTimeDeparture());
        }
        return set;
    }

    @Override
    public Schedule getSchedulesByIdSchedule(int id) {
        return scheduleRepo.getSchedulesByIdSchedule(id);
    }


}
