package com.example.casemd6be.controller.Admin;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.repository.son.IAccountRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
public class AdminAPI {
    @Autowired
    IAccountRepoS iAccountRepo;

    @GetMapping("/showAccount/{id}")
    public ResponseEntity<Iterable<Account>> showAccountByRoles(@PathVariable ("id") Long id) {
        Iterable<Account> accounts = iAccountRepo.findByIdRoles(id);
        if (!accounts.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/seach")
    public ResponseEntity<Iterable<Account>> showAllByName(@RequestParam("search") String search) {
        Iterable<Account> accounts1 =iAccountRepo.findAccountByUsername(search);
        if (!accounts1.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts1, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Account> showOne(@PathVariable("id") Long id) {
        Optional<Account> account = iAccountRepo.findById(id);
        if (!account.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }

}


