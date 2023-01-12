package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    private String detail;
    private Double price ;
    private long amount ;


    private long amountsell;


    private String img;

    @ManyToOne(cascade=CascadeType.MERGE)
    private Category category;

    @ManyToOne(cascade=CascadeType.MERGE)
    private Shop shop;
}
