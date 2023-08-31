package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepo extends JpaRepository<Ticket,Integer> {
}
