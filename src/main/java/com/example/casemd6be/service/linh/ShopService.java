package com.example.casemd6be.service.linh;

import com.example.casemd6be.model.Shop;
import com.example.casemd6be.repository.linh.IShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    IShopRepo iShopRepo;
    public Shop findShopById(long id){
        return iShopRepo.findShopById(id);
    }
}
