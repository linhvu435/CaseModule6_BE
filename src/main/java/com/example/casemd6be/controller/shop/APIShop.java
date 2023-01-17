package com.example.casemd6be.controller.shop;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.model.Shop;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.repository.manh.IShopRepoM;
import com.example.casemd6be.service.linh.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/shop")
public class APIShop {
    @Autowired
    ShopService shopService;
    @Autowired
    private IAccountRepo iAccountRepo;
    @Autowired
    private IShopRepoM iShopRepoM;


    @GetMapping("/editnameshop/{name}")
    public ResponseEntity<?> getallproductmyshop(@PathVariable String name) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Shop shop = iShopRepoM.findShopByAccountId(account.getId());
        shop.setName(name);
        iShopRepoM.save(shop);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findShopById(@PathVariable long id) {
            Shop shop1 = shopService.findShopByAccountId(id);
        return new ResponseEntity<>(shop1, HttpStatus.OK);
    }
    @GetMapping("/shop/{id}")
    public ResponseEntity findShop(@PathVariable long id) {
        return new ResponseEntity<>(shopService.findShopByAccountId(id), HttpStatus.OK);
    }
    @GetMapping("/shopId/{id}")
    public Long FindIdShopByProductId(@PathVariable long id) {
        return shopService.FindShopIdByProductId(id);
    }



}
