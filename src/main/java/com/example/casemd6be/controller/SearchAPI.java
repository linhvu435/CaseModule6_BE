package com.example.casemd6be.controller;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.ICategoryM;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.example.casemd6be.repository.manh.IShopRepoM;
import com.example.casemd6be.repository.manh.ITrademarkRepoM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/search")

public class SearchAPI {
    @Autowired
    private IProductRepoM iProductRepo;

    @Autowired
    ICategoryM iCategoryM;


    @GetMapping("/getallcategory")
    public ResponseEntity<?> getallcategory() {
        return new ResponseEntity<>(iCategoryM.findAllCategory(), HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name) {
        return new ResponseEntity<>(iProductRepo.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/searchcategory/{category_id}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable long category_id) {
        List<Product> products = iProductRepo.findProductByCategory_Id(category_id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/searchcategoryandname/{name}/{category_id}")
    public ResponseEntity<List<Product>> findByCategoryAndName(@PathVariable long category_id, @PathVariable String name) {
        return new ResponseEntity<>(iProductRepo.findProductByCategory_IdAndName(name, category_id), HttpStatus.OK);


    }

    @GetMapping("/searchprice/{pricemin}/{pricemax}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable double pricemin, @PathVariable double pricemax) {
        List<Product> products = iProductRepo.findAllP();
        List<Product> productList = new ArrayList<>();
        if (pricemin < pricemax) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPrice() <= pricemax && products.get(i).getPrice() >= pricemin) {
                    productList.add(products.get(i));
                }
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    @GetMapping("/searchpriceandname/{pricemin}/{pricemax}/{name}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable double pricemin, @PathVariable double pricemax,@PathVariable String name) {
        List<Product> products = iProductRepo.findByName(name);
        List<Product> productList = new ArrayList<>();
        if (pricemin < pricemax) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPrice() <= pricemax && products.get(i).getPrice() >= pricemin) {
                    productList.add(products.get(i));
                }
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }


    @GetMapping("/searchpriceandcategory/{pricemin}/{pricemax}/{category_id}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable double pricemin, @PathVariable double pricemax, @PathVariable long category_id) {
        List<Product> products = iProductRepo.findAllP();
        List<Product> productList = new ArrayList<>();
        List<Product> products1 = iProductRepo.findProductByCategory_Id(category_id);
        if (pricemin < pricemax) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPrice() <= pricemax && products.get(i).getPrice() >= pricemin) {
                    for (int j = 0; j < products1.size(); j++) {
                        if (products1.get(j).getId() == products.get(i).getId()) {
                            productList.add(products1.get(j));
                        }
                    }
                }
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    @GetMapping("/searchpriceandcategoryandname/{pricemin}/{pricemax}/{category_id}/{name}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable double pricemin, @PathVariable double pricemax, @PathVariable long category_id,@PathVariable String name) {
        List<Product> products = iProductRepo.findProductByCategory_IdAndName(name, category_id);
        List<Product> products2 = new ArrayList<>();
        List<Product> products1 = iProductRepo.findProductByCategory_Id(category_id);
        if (pricemin < pricemax) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPrice() <= pricemax && products.get(i).getPrice() >= pricemin) {
                    for (int j = 0; j < products1.size(); j++) {
                        if (products1.get(j).getId() == products.get(i).getId()) {
                            products2.add(products1.get(j));
                        }
                    }
                }
            }
            return new ResponseEntity<>(products2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(products2, HttpStatus.OK);
        }
    }
}
