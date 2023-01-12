package com.example.casemd6be.controller;

import com.example.casemd6be.model.ImgProduct;
import com.example.casemd6be.service.linh.ImgProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/imgProduct")
public class APIImgProdcut {
    @Autowired
    ImgProductService imgProductService;
    @PostMapping
    public void saveImgProduct(ImgProduct imgProduct){
        imgProductService.saveImgProduct(imgProduct);
    }
}
