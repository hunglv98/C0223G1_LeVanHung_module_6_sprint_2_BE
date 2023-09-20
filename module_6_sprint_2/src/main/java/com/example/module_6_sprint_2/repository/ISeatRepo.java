package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISeatRepo extends JpaRepository<Seat,Integer> {
    List<Seat> findAllBySchedule_IdSchedule(int id);
    Page<Seat> findAllBySchedule_IdScheduleAndTypeSeat_IdTypeSeat(Pageable pageable,int idSchedule, int typeSeat);

    Seat getSeatByIdSeatAndFlagPaymentIsFalse(int id);
}
