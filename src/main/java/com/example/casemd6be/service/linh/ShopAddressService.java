package com.example.casemd6be.service.linh;

import com.example.casemd6be.repository.linh.IShopAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopAddressService {
    @Autowired
    IShopAddressRepo iShopAddressRepo;
    public String findShopAdressName (long id){
        return iShopAddressRepo.findShopAddressNameByShopId(id);
    }
}
