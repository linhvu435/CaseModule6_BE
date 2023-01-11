package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.DTO.ShopDetailDTO;

import java.util.Comparator;

public class Soft implements Comparator<ShopDetailDTO> {
    @Override
    public int compare(ShopDetailDTO o1, ShopDetailDTO o2) {
        long amount1 = o1.getTotalproductsell();
        long amount2 = o2.getTotalproductsell();
        if(amount1<amount2){
            return 1;
        }else if(amount1==amount2){
            return 0;
        }else{
            return -1;
        }
    }
    }

