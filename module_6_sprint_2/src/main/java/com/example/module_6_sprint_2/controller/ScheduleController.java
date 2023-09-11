package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.model.Schedule;
import com.example.module_6_sprint_2.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private IScheduleService scheduleService;
    @GetMapping("/getAllDate")
    private ResponseEntity<?> getListDate(){
        Set<String> set = scheduleService.findByDateDeparture();
        return new ResponseEntity<>(set,HttpStatus.OK);
    }

    @GetMapping("/getAllTime")
    private ResponseEntity<?> getListTime(){
        Set<String> set = scheduleService.findByTimeDeparture();
        return new ResponseEntity<>(set,HttpStatus.OK);
    }

    @GetMapping("/getSchedule")
    private ResponseEntity<?> getScheduleByDateAndTime(@RequestParam String date, @RequestParam String time){
        Schedule schedule = scheduleService.findByDateDepartureAndTimeDeparture(date, time);
        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedule.getIdSchedule(),HttpStatus.OK);
    }
}
