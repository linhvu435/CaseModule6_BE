package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Bill;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.IBillRepoM;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.son.IAccountRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/order")
public class OrderAPI {

    @Autowired
    IBillRepoM iBillRepoM;
    @Autowired
    private IAccountRepoS iAccountRepo;

    @Autowired
    private IProductRepoM iProductRepoM;

    @GetMapping("/getallp")
    public ResponseEntity<List<Product>> getallPByShop(){
        return new ResponseEntity<>(iProductRepoM.findAllP(),HttpStatus.OK);
    }

    @GetMapping("/getallbill")
    public ResponseEntity<List<Bill>> getall() {
        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }

    @GetMapping("/searchBill/{nameP}")
    public ResponseEntity<List<Bill>> searchbill(@PathVariable String nameP) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());

        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }

    @PostMapping("/createbill")
    public ResponseEntity<Bill> searchbill(@RequestBody Bill bill) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername("manh12");
        List<Product> products = new ArrayList<>();
        products.add((Product) bill.getProduct());
        bill.setAccount(account);
        bill.setDate(LocalDate.now());
        bill.setStatus(1);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}