package com.example.casemd6be.controller.Admin;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.repository.son.IAccountRepoS;
import com.example.casemd6be.repository.son.IRoloesRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
public class AdminAPI {
    @Autowired
    IAccountRepoS iAccountRepo;

    @Autowired
    IRoloesRepoS iRoloesRepoS;

    @GetMapping("/showAccount/{id}")
    public ResponseEntity<Iterable<Account>> showAccountByRoles(@PathVariable("id") Long id) {
        Iterable<Account> accounts = iAccountRepo.findByIdRoles(id);
        if (!accounts.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/seach/{username}")
    public ResponseEntity<Iterable<Account>> showAllByName(@PathVariable("username") String username) {
        Iterable<Account> accounts1 = iAccountRepo.findAccountByUsername(username);
        if (!accounts1.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts1, HttpStatus.OK);
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<Account> showOne(@PathVariable("id") Long id) {
        Optional<Account> account = iAccountRepo.findById(id);
        if (!account.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@RequestBody Account accountEdit, @PathVariable("id") Long id) {
        Optional<Account> accounts = iAccountRepo.findById(id);
    accountEdit.setId(accounts.get().getId());
    accountEdit.setPassword(accounts.get().getPassword());
    accountEdit =iAccountRepo.save(accountEdit);
        return new ResponseEntity<>(accountEdit, HttpStatus.OK);
    }

    @GetMapping("/showrole")
    public ResponseEntity<Iterable<Roles>>showRole(){
        Iterable<Roles> roles = iRoloesRepoS.findAll();
        if (!roles.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}


