package com.example.casemd6be.service;

import com.example.casemd6be.model.Roles;

public interface IRolesService {
    Iterable<Roles> findAll();


    void save(Roles role);

    Roles findByName(String name);
}
