package com.example.casemd6be.service;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.IProductRepoM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    IProductRepoM iProductRepo;

    public void save(Product product) {
        iProductRepo.save(product);
    }

    public Iterable<Product> getAll() {
        return iProductRepo.findAll();
    }

    public void delete(long id) {
        iProductRepo.deleteById(id);
    }

    public Product finByID(long id) {
        return iProductRepo.findById(id).get();
    }
}
