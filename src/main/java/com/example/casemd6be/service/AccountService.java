package com.example.casemd6be.service;


import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.findByUsername(username);
        List<Roles> roles = new ArrayList<>(); // your list of roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
// pass the 'authorities' list to the method that expects a Collection<? extends GrantedAuthority>

        roles.add((Roles) account.getRoles());

        if (account != null) {
            return new User(account.getUsername(), account.getPassword(), authorities);        }
        return null;
    }

    public List<Account> getAll() {
        return (List<Account>) iAccountRepo.findAll();
    }

    public Account findByName(String name) {
        return iAccountRepo.findByUsername(name);
    }
    public Account findbysdt(String phoneNumber) {
        return iAccountRepo.findByPhoneNumber(phoneNumber);
    }


}
