package com.example.casemd6be.service.linh;

import com.example.casemd6be.model.ImgProduct;
import com.example.casemd6be.repository.linh.IImgProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgProductService {
    @Autowired
    IImgProductRepo iImgProductRepo;
    public void saveImgProduct(ImgProduct imgProduct){
        iImgProductRepo.save(imgProduct);
    }
}
