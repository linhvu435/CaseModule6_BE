package com.example.casemd6be.service.linh;

import com.example.casemd6be.model.Shop;
import com.example.casemd6be.repository.linh.IShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    IShopRepo iShopRepo;
    public Shop findShopByAccountId(long id){
        return iShopRepo.findShopByAccountId(id);
    }
    public Long findIdShopAddressByIdAccount(long id){
        return iShopRepo.findIdShopAddressByIdAccount(id);
    }

    public Shop findshopbyidaccount(long id){
        return iShopRepo.findShopByAccountId(id);
    }

    public Shop findById(long id) {
        return iShopRepo.findById(id).get();
    }

    public Long FindShopIdByProductId(long id){
        return iShopRepo.FindShopIdByProductId(id);
    }

}
