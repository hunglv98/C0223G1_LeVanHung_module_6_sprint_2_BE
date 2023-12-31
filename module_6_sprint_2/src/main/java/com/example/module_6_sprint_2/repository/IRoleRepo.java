package com.example.module_6_sprint_2.repository;

import com.example.module_6_sprint_2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role,Integer> {
    Role findByIdRole(int id);
}
