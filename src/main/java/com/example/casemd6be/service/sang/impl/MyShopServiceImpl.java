package com.example.casemd6be.service.sang.impl;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Shop;
import com.example.casemd6be.repository.sang.MyShopRepo;
import com.example.casemd6be.service.sang.IMyShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyShopServiceImpl implements IMyShopService {

    @Autowired
    private MyShopRepo myShopRepo;

    @Override
    public void save(Shop shop) {
        myShopRepo.save(shop);
    }

    @Override
    public Optional<Shop> findMyShopById(Long id) {
        return Optional.ofNullable(myShopRepo.findMyShopById(id));
    }

    @Override
    public Iterable<Shop> findAll() {
        return myShopRepo.findAll();
    }

}
