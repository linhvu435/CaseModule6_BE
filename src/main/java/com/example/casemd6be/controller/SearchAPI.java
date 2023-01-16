package com.example.casemd6be.controller;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.linh.IShopRepo;
import com.example.casemd6be.repository.manh.ICategoryM;
import com.example.casemd6be.repository.manh.IProductRepoM;
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
    private IProductRepoM iProductRepoM;

    @Autowired
    private IShopRepo iShopRepo;
    @Autowired
    ICategoryM iCategoryM;

    @Autowired
    private ITrademarkRepoM iTrademarkRepo;

    @GetMapping("/getallcategory")
    public ResponseEntity<?> getallcategory() {
        return new ResponseEntity<>(iCategoryM.findAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/getallproduct")
    public ResponseEntity<?> getallproduct() {
        return new ResponseEntity<>(iProductRepoM.findAllP(), HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name) {
        return new ResponseEntity<>(iProductRepoM.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/searchcategory/{category_id}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable long category_id) {
        List<Product> products = iProductRepoM.findProductByCategory_Id(category_id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/searchcategoryandname/{category_id}/{name}")
    public ResponseEntity<List<Product>> findByCategoryAndName(@PathVariable long category_id, @PathVariable String name) {
        return new ResponseEntity<>(iProductRepoM.findProductByCategory_IdAndName(name, category_id), HttpStatus.OK);
    }

    @GetMapping("/searchprice/{pricemin}/{pricemax}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable double pricemin, @PathVariable double pricemax) {
        List<Product> products = iProductRepoM.findAllP();
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
        List<Product> products = iProductRepoM.findByName(name);
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
        List<Product> products = iProductRepoM.findAllP();
        List<Product> productList = new ArrayList<>();
        List<Product> products1 = iProductRepoM.findProductByCategory_Id(category_id);
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
        List<Product> products = iProductRepoM.findProductByCategory_IdAndName(name, category_id);
        List<Product> products2 = new ArrayList<>();
        List<Product> products1 = iProductRepoM.findProductByCategory_Id(category_id);
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
