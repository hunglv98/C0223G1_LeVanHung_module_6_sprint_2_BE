package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Seat;
import com.example.module_6_sprint_2.repository.ISeatRepo;
import com.example.module_6_sprint_2.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {
    @Autowired
    private ISeatRepo seatRepo;
    @Override
    public List<Seat> findAllBySchedule_IdSchedule(int id) {
        return seatRepo.findAllBySchedule_IdSchedule(id);
    }

    @Override
    public Page<Seat> findAllBySchedule_IdScheduleAndTypeSeat_IdTypeSeat(Pageable pageable, int idSchedule, int typeSeat) {
        return seatRepo.findAllBySchedule_IdScheduleAndTypeSeat_IdTypeSeat(pageable,idSchedule,typeSeat);
    }

    @Override
    public Seat getSeatByIdSeat(int id) {
        return seatRepo.getSeatByIdSeatAndFlagPaymentIsFalse(id);
    }

    @Override
    public void save(Seat seat) {
        seatRepo.save(seat);
    }
}
