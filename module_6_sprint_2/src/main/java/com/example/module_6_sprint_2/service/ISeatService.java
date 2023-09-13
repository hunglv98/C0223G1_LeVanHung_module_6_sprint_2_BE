package com.example.module_6_sprint_2.service;

import com.example.module_6_sprint_2.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISeatService {
    List<Seat> findAllBySchedule_IdSchedule(int id);
    Page<Seat> findAllBySchedule_IdScheduleAndTypeSeat_IdTypeSeat(Pageable pageable, int idSchedule, int typeSeat);
    Seat getSeatByIdSeat(int id);
    void save(Seat seat);
}
