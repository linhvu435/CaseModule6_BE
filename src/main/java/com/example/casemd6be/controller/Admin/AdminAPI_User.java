package com.example.casemd6be.controller.Admin;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.repository.son.IAccountRepoS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/User")
public class AdminAPI_User {
    IAccountRepoS iAccountRepo;
    @GetMapping("/number")
    public ResponseEntity<Iterable<Account>> showAccountByNumber(@RequestParam("number") String number) {
        Iterable<Account> accounts1 = iAccountRepo.findByPhoneNumber(number);
        if (!accounts1.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts1, HttpStatus.OK);
    }

}
