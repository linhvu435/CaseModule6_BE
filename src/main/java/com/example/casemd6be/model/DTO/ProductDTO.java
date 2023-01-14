package com.example.casemd6be.model.dto;

import com.example.casemd6be.model.Category;
import com.example.casemd6be.model.ImgProduct;
import com.example.casemd6be.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.List;
@Data
@AllArgsConstructor
public class ProductDTO {
    private long id;

    private String name;


    private String detail;
    private Double price ;
    private long amount ;

    private Category category;

    private Shop shop;
    private List<ImgProduct> imgProducts;
}
