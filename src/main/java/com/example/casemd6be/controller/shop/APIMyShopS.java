package com.example.casemd6be.controller.shop;


import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Shop;
import com.example.casemd6be.service.sang.impl.MyShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Validated
@RestController
@CrossOrigin("*")
@RequestMapping("/myShop")
public class APIMyShopS {

    @Autowired
    private MyShopServiceImpl myShopService;

    @GetMapping("/allShop")
    public ResponseEntity<Iterable<Shop>> showMyShop() {
        Iterable<Shop> shops = myShopService.findAll();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Shop> updateMyShopProfile(@PathVariable Long id, @RequestBody Shop shop){
        Optional<Shop> myshop = this.myShopService.findMyShopById(id);
        if (!myshop.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
//            shop.setId(myshop.get().getId());
            shop.setName(myshop.get().getName());
            shop.setPhone(myshop.get().getPhone());
            shop.setImg(myshop.get().getImg());
            shop.setShopAddress(myshop.get().getShopAddress());
            myShopService.save(shop);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        }
    }
}
