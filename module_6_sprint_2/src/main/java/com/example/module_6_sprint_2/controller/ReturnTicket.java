package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.model.Seat;
import com.example.module_6_sprint_2.model.Ticket;
import com.example.module_6_sprint_2.service.ICustomerService;
import com.example.module_6_sprint_2.service.ISeatService;
import com.example.module_6_sprint_2.service.ITicketService;
import com.example.module_6_sprint_2.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ticketReturn")
public class ReturnTicket {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITicketService ticketService;

    @Autowired
    private ISeatService seatService;

    @Autowired
    private MailService mailService;

    @GetMapping("/code")
    public ResponseEntity<?> getTicketFromCode(@RequestParam String code, @RequestParam String tel, @RequestParam String email) {
        Ticket ticket = ticketService.getTicketByCodeTicket(code);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (ticket.getCustomer().getTelCustomer().equals(tel) && ticket.getCustomer().getEmailCustomer().equals(email)) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> returnTicketById(@PathVariable int id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LocalDate date = LocalDate.parse(ticket.getSeat().getSchedule().getDateDeparture());
        LocalTime time = LocalTime.parse(ticket.getSeat().getSchedule().getTimeDeparture());
        if(date.isAfter(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
            Seat seat = ticket.getSeat();
            seat.setFlagPayment(false);
            seatService.save(seat);
            ticket.setFlagCancel(true);
            ticketService.save(ticket);
            return new ResponseEntity<>(HttpStatus.OK);
        }else if (time.isAfter(LocalTime.now().plusMinutes(60))&& date.isEqual(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
            Seat seat = ticket.getSeat();
            seat.setFlagPayment(false);
            seatService.save(seat);
            ticket.setFlagCancel(true);
            ticketService.save(ticket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> sendCodeToEmail(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        String timeNow = String.valueOf(LocalDateTime.now());
        String str[] = timeNow.split("T");

        List<Ticket> list = ticketService.findByCustomer_EmailCustomerAndFlagCancelIsFalseAndSeat_Schedule_DateDeparture(email,str[0]);
        if (list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Ticket> listResult = new ArrayList<>();
        for (Ticket t:list) {
            LocalDate date = LocalDate.parse(t.getSeat().getSchedule().getDateDeparture());
            LocalTime time = LocalTime.parse(t.getSeat().getSchedule().getTimeDeparture());
            if(date.isAfter(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
                listResult.add(t);
            }else if (time.isAfter(LocalTime.now().plusMinutes(60))&& date.isEqual(ChronoLocalDate.from(LocalDateTime.now().plusMinutes(60)))){
                listResult.add(t);
            }
        }
        if (listResult.size() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        mailService.sendMail(email,listResult);
        return new ResponseEntity<>(listResult,HttpStatus.OK);
    }
}
