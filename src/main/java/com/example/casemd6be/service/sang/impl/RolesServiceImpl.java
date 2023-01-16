package com.example.casemd6be.service.sang.impl;

import com.example.casemd6be.model.Roles;
import com.example.casemd6be.repository.sang.RolesRepo;
import com.example.casemd6be.service.sang.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements IRolesService {

    @Autowired
    private RolesRepo rolesRepo;

    @Override
    public Iterable<Roles> findAll() {
        return rolesRepo.findAll();
    }

    @Override
    public void save(Roles role) {
        rolesRepo.save(role);
    }

    @Override
    public Roles findByName(String name) {
        return rolesRepo.findByName(name);
    }

}
