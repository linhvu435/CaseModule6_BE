package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Bill;
import com.example.casemd6be.model.DTO.ShopDetailDTO;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.model.Shop;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.manh.IShopRepoM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/showproduct")
public class ProductAPI {
    @Autowired
    IProductRepoM iProductRepoM;
    @Autowired
    private IShopRepoM iShopRepoM;
    @Autowired
    private IAccountRepo iAccountRepo;

    @GetMapping("/getnewproduct")
    public ResponseEntity<?> getall() {
        List<Product> products = iProductRepoM.findAllP();
        for (int i = 0,j= products.size()-1;i<j; i++) {
            Product product = products.remove(j);
            products.add(i,product);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/gettopsellproduct")
    public ResponseEntity<?> gettopsellp() {
        List<Product> products = iProductRepoM.findAllTopProduct();
        List<Product> products1 = new ArrayList<>();
        if (products.size() >=10){
            for (int i = 0; i < 10; i++) {
                products1.add(products.get(i));
            }
        }else {
        for (int i = 0; i < products.size(); i++) {
            products1.add(products.get(i));
        }
        }
        return new ResponseEntity<>(products1, HttpStatus.OK);
    }

    @GetMapping("/gettopshop")
    public ResponseEntity<?> gettopshop() {
        List<Shop> shops = iShopRepoM.findAllShop();
        List<Product> products ;
        ShopDetailDTO shopDetailDTOS ;
        List<ShopDetailDTO> shopDetailDTOS1 = new ArrayList<>();
        for (int i = 0; i < shops.size(); i++) {
            products=iProductRepoM.findProductByShopId(shops.get(i).getId());
            long cu = 0;
            for (int j = 0; j < products.size(); j++) {
                cu+= products.get(j).getAmountsell();
            }
            shopDetailDTOS = new ShopDetailDTO(shops.get(i).getId(),cu,shops.get(i).getName(),shops.get(i).getShopAddress(),products);
            shopDetailDTOS1.add(shopDetailDTOS);
            products=new ArrayList<>();
        }
        Collections.sort(shopDetailDTOS1,new Soft());
        return new ResponseEntity<>(shopDetailDTOS1, HttpStatus.OK);
    }
}
