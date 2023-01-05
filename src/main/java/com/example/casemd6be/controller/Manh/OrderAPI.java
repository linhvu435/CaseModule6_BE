package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Bill;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.IBillRepoM;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.son.IAccountRepoS;
import com.example.casemd6be.repository.son.IProductRepoS;
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

    @Autowired
    IProductRepoS iProductRepoS;

    @GetMapping("/getallp")
    public ResponseEntity<List<Product>> getallPByShop(){
        return new ResponseEntity<>(iProductRepoM.findAllP(),HttpStatus.OK);
    }

    @GetMapping("/getallbill")
    public ResponseEntity<List<Bill>> getall() {
        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }

    @GetMapping("/showBillShop")
    public ResponseEntity<List<Product>> showbillshop() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills = iBillRepoM.findAllB();
        List<Bill> billshop = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < bills.size(); i++) {
            products.add((Product) bills.get(i).getProduct());
        }
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getShop().getAccount().getId()!=account.getId()){
                products.remove(products.get(i));
            }
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/searchBill/{name}")
    public ResponseEntity<List<Bill>> searchbill(@PathVariable String name) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Bill> bills = iBillRepoM.findAllB();
        List<Product> products = iProductRepoM.findByName(name);
        List<Bill> billList =new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < bills.size(); j++) {

            }
        }
        return new ResponseEntity<>(iBillRepoM.findAllB(), HttpStatus.OK);
    }




        @PostMapping("/createbill")
    public ResponseEntity<Bill> searchbill(@RequestBody Bill bill) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        List<Product> products1 = bill.getProduct();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < products1.size(); i++) {
            products.add(iProductRepoS.findProductById(products1.get(i).getId()));
        }
        double sum = 0;
        for (int i = 0; i < products.size(); i++) {
            sum += products.get(i).getPrice();
        }

        bill.setTotalprice(sum);
        bill.setProduct(products);
        bill.setAccount(account);
        bill.setDate(LocalDate.now());
        bill.setStatus(1);

        iBillRepoM.save(bill);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}