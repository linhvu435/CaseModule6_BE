package com.example.casemd6be.service.sang.impl;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.repository.sang.AccountRepo;
import com.example.casemd6be.service.sang.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        List<Roles> roles = new ArrayList<>();
        roles.add(account.getRoles());
        if (account != null) {
            return new User(account.getUsername(), account.getPassword(), (Collection<? extends GrantedAuthority>) roles);
        }
        return null;
    }
    @Override
    public void save(Account account) {
        accountRepo.save(account);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public Account getCurrentUser() {
        Account account;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        account = this.findByUsername(userName);
        return account;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepo.findById(id);
    }


    @Override
    public boolean checkLogin(Account account) {
        Iterable<Account> accounts = this.findAll();
        boolean isCorrectUser = false;
        for (Account currentAccount : accounts) {
            if (currentAccount.getUsername().equals(account.getUsername())
                    && account.getPassword().equals(currentAccount.getPassword())
                   ) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegister(Account account) {
        boolean isRegister = false;
        Iterable<Account> accounts = this.findAll();
        for (Account currentAccount : accounts) {
            if (account.getUsername().equals(currentAccount.getUsername())) {
                isRegister = true;
                break;
            }
        }
        return isRegister;
    }

    public Account findByName(String name) {
        return accountRepo.findByUsername(name);
    }
    public Account findbysdt(String  phoneNumber) {
        return accountRepo.findByPhoneNumber( phoneNumber);
    }
    public Account findbyEmail(String email){
        return accountRepo.findbyEmail(email);
    }

}
