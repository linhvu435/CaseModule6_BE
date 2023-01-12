package com.example.casemd6be.service;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.repository.manh.IProductRepoM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepoM iProductRepoM;

    public void save(Product product) {
        iProductRepoM.save(product);
    }

    public Iterable<Product> getAll() {
        return iProductRepoM.findAll();
    }

    public void delete(long id) {
        iProductRepoM.deleteById(id);
    }

    public Product finByID(long id) {
        return iProductRepoM.findById(id).get();
    }
    public List<Product> getProductByShopId(long id){
        return iProductRepoM.findProductByShopId(id);
    }

    public Long FindMaxIdProduct(){
      return  iProductRepoM.FindMaxIdProduct();
    }

    public Product findProductById(long id){
        return iProductRepoM.findById(id).get();
    }
}
