package com.example.casemd6be.controller;



import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.dto.UserToken;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.service.AccountService;
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
@RequestMapping("/login")
public class LoginAPI {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtService jwtService;

  @Autowired
  IAccountRepo iAccountRepo;
  @Autowired
  AccountService accountService;
  @GetMapping()
  public List<Account> getAccount() {
    return (List<Account>) iAccountRepo.findAll();
  }

  @PostMapping()
  public ResponseEntity<UserToken> login(@RequestBody Account account) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtService.generateTokenLogin(authentication);
    Account account1 = accountService.findByName(account.getUsername());
    UserToken userToken = new UserToken(account.getUsername(), account1.getRoles(), token, account1.getId());
    return new ResponseEntity<>(userToken, HttpStatus.OK);
  }

}
