package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Ticket;
import com.example.module_6_sprint_2.repository.ITicketRepo;
import com.example.module_6_sprint_2.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepo ticketRepo;
    @Override
    public void save(Ticket ticket) {
        ticketRepo.save(ticket);
    }

    @Override
    public Ticket getTicketByCodeTicket(String code) {
        return ticketRepo.getTicketByCodeTicketAndFlagCancelIsFalse(code);
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRepo.getTicketByIdTicketAndFlagCancelIsFalse(id);
    }

    @Override
    public List<Ticket> findByCustomer_EmailCustomerAndFlagCancelIsFalse(String email) {
        return ticketRepo.findByCustomer_EmailCustomerAndFlagCancelIsFalse(email);
    }

    @Override
    public List<Ticket> findByCustomer_EmailCustomerAndFlagCancelIsFalseAndSeat_Schedule_DateDeparture(String email, String date) {
        return ticketRepo.findByCustomer_EmailCustomerAndFlagCancelIsFalseAndSeat_Schedule_DateDepartureGreaterThanEqual(email,date);
    }

    @Override
    public boolean existsBySeat_Schedule_DateDeparture(String date) {
        return ticketRepo.existsBySeat_Schedule_DateDepartureGreaterThan(date);
    }

    @Override
    public boolean existsBySeat_Schedule_TimeDeparture(String time) {
        return ticketRepo.existsBySeat_Schedule_TimeDepartureGreaterThanEqual(time);
    }

    @Override
    public List<Ticket> findByCustomer_IdCustomer(int id) {
        return ticketRepo.findByCustomer_IdCustomerAndFlagCancelIsFalse(id);
    }

    @Override
    public Page<Ticket> findByCustomer_IdCustomerAndCodeTicketContainingAndSeat_Schedule_DateDepartureAndSeat_Schedule_TimeDeparture(Pageable pageable, int id, String code, String date, String time) {
        return ticketRepo.findByCustomer_IdCustomerAndFlagCancelIsFalseAndCodeTicketContainingAndSeat_Schedule_DateDepartureContainingAndSeat_Schedule_TimeDepartureContaining(pageable, id, code, date, time);
    }


}
