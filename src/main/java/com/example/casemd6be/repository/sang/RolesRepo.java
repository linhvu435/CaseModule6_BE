package com.example.casemd6be.repository.sang;

import com.example.casemd6be.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles, Long> {
    Roles findByName(String roleName);
}
