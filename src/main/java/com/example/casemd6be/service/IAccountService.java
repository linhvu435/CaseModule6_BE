package com.example.casemd6be.service;

import com.example.casemd6be.model.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAccountService extends UserDetailsService {

    void save (Account account);

    Iterable<Account> findAll();

    Account findByUsername(String username);

    Account getCurrentUser();

    Optional<Account> findById(Long id);

    Account findByName(String name);
    Account findbysdt(String phoneNumber);
    Account findbyEmail(String email);
    boolean checkLogin(Account account);

    boolean isRegister(Account account);



}
