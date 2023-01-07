package com.example.casemd6be.controller.shop;

import com.example.casemd6be.model.Category;
import com.example.casemd6be.service.linh.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class APICategory {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public Iterable<Category> getAll(){
        return categoryService.getAll();
    }
}
