package com.example.casemd6be.controller.Admin;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.son.IProductRepoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/product")
public class AdminProduct {

    @Autowired
    IProductRepoS iProductRepo;

    @GetMapping("/showProduct/{id}")
    public ResponseEntity<Iterable<Product>> showProductByCategory(@PathVariable("id") Long id) {
        Iterable<Product> products = iProductRepo.findByIdCategory(id);
        if (!products.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showOne(@PathVariable("id") Long id) {
        Optional<Product> product = iProductRepo.findById(id);
        if (!product.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product>delele(@PathVariable("id") Long id){
        Optional<Product> product = iProductRepo.findById(id);
        if (!product.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductRepo.deleteById(id);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }


}
