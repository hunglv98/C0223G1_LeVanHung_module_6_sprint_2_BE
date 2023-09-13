package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Ticket;
import com.example.module_6_sprint_2.repository.ITicketRepo;
import com.example.module_6_sprint_2.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepo ticketRepo;
    @Override
    public void save(Ticket ticket) {
        ticketRepo.save(ticket);
    }
}
