package com.example.casemd6be.service.sang;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Shop;

import java.util.Optional;

public interface IMyShopService {

    void save (Shop shop);

    Optional<Shop> findMyShopById(Long id);

    Iterable<Shop> findAll();
}
