package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String img;
    private String detail;
    private Double price ;
    private long amount ;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Trademark trademark;


    @ManyToOne
    private Shop shop;

    public Product(String name, String img, String detail, Double price, long amount, Category category, Trademark trademark, Shop shop) {
        this.name = name;
        this.img = img;
        this.detail = detail;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.trademark = trademark;
        this.shop = shop;
    }

    public Product() {

    }
}
