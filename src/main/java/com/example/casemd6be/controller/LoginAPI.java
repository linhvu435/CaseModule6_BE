package com.example.casemd6be.controller;



import com.example.casemd6be.model.Account;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginAPI {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtService jwtService;

  @Autowired
  IAccountRepo iAccountRepo;

  @GetMapping("/getallaccount")
  public List<Account> getAccount() {
    return (List<Account>) iAccountRepo.findAll();
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody Account account) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtService.createToken(authentication);
      return new ResponseEntity<>(token, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
  }
}
