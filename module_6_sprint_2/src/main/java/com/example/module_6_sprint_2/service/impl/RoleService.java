package com.example.module_6_sprint_2.service.impl;

import com.example.module_6_sprint_2.model.Role;
import com.example.module_6_sprint_2.repository.IRoleRepo;
import com.example.module_6_sprint_2.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepo roleRepo;
    @Override
    public Role findByIdRole(int id) {
        return roleRepo.findByIdRole(id);
    }
}
