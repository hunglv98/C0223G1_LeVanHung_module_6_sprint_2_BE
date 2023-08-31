package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeatRepo extends JpaRepository<Seat,Integer> {
}
