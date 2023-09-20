package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.model.Schedule;
import com.example.module_6_sprint_2.model.Seat;
import com.example.module_6_sprint_2.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private IScheduleService scheduleService;
    @GetMapping("/getAllDate")
    public ResponseEntity<?> getListDate(){
        Set<String> set = scheduleService.findByDateDeparture();
        return new ResponseEntity<>(set,HttpStatus.OK);
    }

    @GetMapping("/getAllTime")
    public ResponseEntity<?> getListTime(){
        Set<String> set = scheduleService.findByTimeDeparture();
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getSchedule")
    public ResponseEntity<?> getScheduleByDateAndTime(@RequestParam String date, @RequestParam String time){
        Schedule schedule = scheduleService.findByDateDepartureAndTimeDeparture(date, time);
        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDate date1 = LocalDate.parse(schedule.getDateDeparture());
        LocalTime time1= LocalTime.parse(schedule.getTimeDeparture());
        if(date1.isAfter(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
            return new ResponseEntity<>(schedule.getIdSchedule(),HttpStatus.OK);
        }else if (time1.isAfter(LocalTime.now().plusMinutes(60))&& date1.isEqual(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
            return new ResponseEntity<>(schedule.getIdSchedule(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable int id){
        Schedule schedule = scheduleService.getSchedulesByIdSchedule(id);
        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDate date = LocalDate.parse(schedule.getDateDeparture());
        LocalTime time = LocalTime.parse(schedule.getTimeDeparture());
        if(date.isAfter(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
            return new ResponseEntity<>(schedule,HttpStatus.OK);
        }else if (time.isAfter(LocalTime.now().plusMinutes(60))&& date.isEqual(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
            return new ResponseEntity<>(schedule,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
