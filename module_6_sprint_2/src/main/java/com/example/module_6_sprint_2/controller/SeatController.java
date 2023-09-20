package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.model.Schedule;
import com.example.module_6_sprint_2.model.Seat;
import com.example.module_6_sprint_2.service.IScheduleService;
import com.example.module_6_sprint_2.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private ISeatService seatService;

    @GetMapping("/{idSchedule}")
    public ResponseEntity<?> getListById(@PathVariable int idSchedule){
        List<Seat> list = seatService.findAllBySchedule_IdSchedule(idSchedule);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSearchByIdScheduleAndIdTypeSeat(@PageableDefault(size = 15)Pageable pageable,
                                                                @RequestParam int idSchedule, @RequestParam int idTypeSeat){
        Page<Seat> list = seatService.findAllBySchedule_IdScheduleAndTypeSeat_IdTypeSeat(pageable,idSchedule,idTypeSeat);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/detail/{listId}")
    public ResponseEntity<?> getListDetail(@PathVariable List<Integer> listId){
        List<Seat> list = listId.stream().map(i->seatService.getSeatByIdSeat(i)).collect(Collectors.toList());
        List<Seat> list1 = new ArrayList<>();
        for (Seat s:list) {
            LocalDate date1 = LocalDate.parse(s.getSchedule().getDateDeparture());
            LocalTime time1= LocalTime.parse(s.getSchedule().getTimeDeparture());
            if(date1.isAfter(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
                list1.add(s);
            }else if (time1.isAfter(LocalTime.now().plusMinutes(60))&& date1.isEqual(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
                list1.add(s);
            }
        }


        if (list1.size() <1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list1,HttpStatus.OK);
    }
}
