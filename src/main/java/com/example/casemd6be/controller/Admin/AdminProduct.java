package com.example.casemd6be.controller.Admin;

import com.example.casemd6be.model.dto.ProductDTO;
import com.example.casemd6be.model.ImgProduct;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.IImgProductRepoM;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.son.IProductRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/product")
public class AdminProduct {
    @Autowired
    IProductRepoS iProductRepoS;
    @Autowired
    private IProductRepoM iProductRepoM;
    @Autowired
    private IImgProductRepoM iImgProductRepo;
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(){
        List<Product> products =  iProductRepoM.findAllP();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            List<ImgProduct> imgProducts = iImgProductRepo.findAllImgByProduct(products.get(i).getId());
            ProductDTO productDTO = new ProductDTO(products.get(i).getId(),products.get(i).getName(),products.get(i).getDetail(),products.get(i).getPrice(),products.get(i).getAmount(),products.get(i).getCategory(), products.get(i).getShop(),imgProducts);
          productDTOS.add(productDTO);
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

}
