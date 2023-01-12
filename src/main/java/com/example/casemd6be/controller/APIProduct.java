package com.example.casemd6be.controller;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class APIProduct {
    @Autowired
    ProductService productService;
    @GetMapping("{id}")
    public Iterable<Product> getAllByShopId(@PathVariable long id){
        return productService.getProductByShopId(id);
    }
    @GetMapping
        public Iterable<Product> getAll(){
            return productService.getAll();
        }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/edit/{id}")
    public ResponseEntity<Product> Edit(@PathVariable long id){
        return new ResponseEntity<>(productService.finByID(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> edit( @RequestBody Product product, @PathVariable Long id) {
        product.setId(id);
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
