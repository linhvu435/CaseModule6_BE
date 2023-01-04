package com.example.casemd6be.controller;


import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Roles;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/registers")
public class RegisterApi {

  @Autowired
  IAccountRepo iAccountRepo;



  @Autowired
  AccountService accountService;

  @PostMapping("/register")
  public ResponseEntity<Account> register(@RequestBody Account account){
    Roles role = new Roles();
    role.setName("ROLE_USER");
    role.setId(1);
    account.setRoles(role);
    account.setStatus(0);
    account.setDate(LocalDate.now());
    iAccountRepo.save(account);
    return new ResponseEntity<>(account, HttpStatus.OK);

  }


//  @GetMapping("/checkUsername")
//  public ResponseEntity<Account> checkUser(@RequestParam String userName) {
//    Account account = accountService.findByName(userName);
//    if (account==null){
//      return new ResponseEntity<>(account,HttpStatus.OK);
//    }else {
//      return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
//    }
//  }
//
//  @GetMapping("/checkPhonenumber")
//  public ResponseEntity<Account> checkPhoneNumber(@RequestParam String phoneNumber) {
//    Account account = accountService.findbysdt(phoneNumber);
//    if (account==null){
//      return new ResponseEntity<>(account,HttpStatus.OK);
//    }else {
//      return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
//    }
//  }
//
//  @GetMapping("/check/{username}&{phoneNumber}")
//  public  ResponseEntity<Account> check( @PathVariable String username , @PathVariable String phoneNumber){
//    Account account1 = iAccountRepo.findAccountByPhoneNumberAndUsername(username, phoneNumber);
//    if (account1 != null) {
//      return new ResponseEntity<>(account1, HttpStatus.OK);
//    } else {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//  }

}
