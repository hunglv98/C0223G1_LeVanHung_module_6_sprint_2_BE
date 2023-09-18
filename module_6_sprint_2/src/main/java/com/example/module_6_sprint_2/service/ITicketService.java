package com.example.module_6_sprint_2.service;

import com.example.module_6_sprint_2.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITicketService {
    void save(Ticket ticket);
    Ticket getTicketByCodeTicket(String code);

    Ticket getTicketById(int id);

    List<Ticket> findByCustomer_EmailCustomerAndFlagCancelIsFalse(String email);

    List<Ticket> findByCustomer_EmailCustomerAndFlagCancelIsFalseAndSeat_Schedule_DateDeparture(String email,String date);

    boolean existsBySeat_Schedule_DateDeparture(String date);
    boolean existsBySeat_Schedule_TimeDeparture(String time);

    List<Ticket> findByCustomer_IdCustomer(int id);

    Page<Ticket> findByCustomer_IdCustomerAndCodeTicketContainingAndSeat_Schedule_DateDepartureAndSeat_Schedule_TimeDeparture(Pageable pageable,int id, String code, String date, String time);
}
