package com.example.module_6_sprint_2.controller;

import com.example.module_6_sprint_2.model.Ticket;
import com.example.module_6_sprint_2.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("*")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/history")
    public ResponseEntity<?> getTicketByIdCustomer(@PageableDefault(size = 8,sort = "dateBooking",direction = Sort.Direction.DESC) Pageable pageable, @RequestParam int idCustomer, @RequestParam String codeTicket, @RequestParam String dateDeparture,
                                                   @RequestParam String timeDeparture){
        Page<Ticket> list = ticketService.findByCustomer_IdCustomerAndCodeTicketContainingAndSeat_Schedule_DateDepartureAndSeat_Schedule_TimeDeparture(pageable,idCustomer,codeTicket,dateDeparture,timeDeparture);
//        if(list.size()<1){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketByIdTicket (@PathVariable int id){
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket,HttpStatus.OK);
    }
}
