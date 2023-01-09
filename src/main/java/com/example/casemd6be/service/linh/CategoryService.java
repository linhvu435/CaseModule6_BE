package com.example.casemd6be.service.linh;

import com.example.casemd6be.model.Category;
import com.example.casemd6be.repository.linh.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepo categoryRepo;
    public Iterable<Category> getAll(){
        return categoryRepo.findAll();
    }
    public Category findById(long id){
        return categoryRepo.findById(id).get();
    }
}
