package com.example.casemd6be.controller.Admin;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.repository.son.IAccountRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/User")
public class AdminAPI_User {
    @Autowired
    IAccountRepoS iAccountRepo;

    @GetMapping("/seach/{phone_number}")
    public ResponseEntity<Iterable<Account>> showAllByName(@PathVariable("phone_number") String phone_number) {
        Iterable<Account> accounts1 = iAccountRepo.findByAccountPhoneNumber(phone_number);
        if (!accounts1.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts1, HttpStatus.OK);
    }

    @GetMapping("/update/{id}/{idstt}")
    public ResponseEntity<?> updaStatus(@PathVariable("id") Long id,@PathVariable("idstt") Long idstt) {
        Account accounts1 = iAccountRepo.findByIdUsername(id);
        accounts1.setStatus(idstt);
        iAccountRepo.save(accounts1);
        return new ResponseEntity<>(accounts1, HttpStatus.OK);
    }

}
