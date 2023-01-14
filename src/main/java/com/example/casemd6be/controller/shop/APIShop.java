package com.example.casemd6be.controller.shop;

import com.example.casemd6be.model.Shop;
import com.example.casemd6be.service.linh.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/shop")
public class APIShop {
    @Autowired
    ShopService shopService;
    @GetMapping("/{id}")
    public ResponseEntity findShopById(@PathVariable long id){
            Shop shop1 =  shopService.findShopById(id);

                      return new ResponseEntity<>(shop1, HttpStatus.OK);

        }


    @GetMapping("/shop/{id}")
    public ResponseEntity findShop(@PathVariable long id){
        return new ResponseEntity<>(shopService.findShopById(id),HttpStatus.OK);
    }
}
