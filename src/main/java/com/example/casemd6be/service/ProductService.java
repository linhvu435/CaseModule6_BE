package com.example.casemd6be.service;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepo iProductRepo;

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
    public List<Product> getProductByShopId(long id){
        return iProductRepo.findProductByShopId(id);
    }
}
