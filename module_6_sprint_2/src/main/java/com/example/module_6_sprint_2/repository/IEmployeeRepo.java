package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee,Integer> {
}
