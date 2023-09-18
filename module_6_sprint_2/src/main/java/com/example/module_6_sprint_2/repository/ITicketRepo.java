package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketRepo extends JpaRepository<Ticket,Integer> {
    Ticket getTicketByCodeTicketAndFlagCancelIsFalse(String code);

    Ticket getTicketByIdTicket(int id);

    List<Ticket> findByCustomer_EmailCustomerAndFlagCancelIsFalse(String email);

    List<Ticket> findByCustomer_EmailCustomerAndFlagCancelIsFalseAndSeat_Schedule_DateDepartureGreaterThanEqual(String email,String date);

    boolean existsBySeat_Schedule_DateDepartureGreaterThan(String date);
    boolean existsBySeat_Schedule_TimeDepartureGreaterThanEqual(String time);

    List<Ticket> findByCustomer_IdCustomerAndFlagCancelIsFalse(int id);

    Page<Ticket> findByCustomer_IdCustomerAndFlagCancelIsFalseAndCodeTicketContainingAndSeat_Schedule_DateDepartureContainingAndSeat_Schedule_TimeDepartureContaining(Pageable pageable, int id, String code, String date, String time);
}
