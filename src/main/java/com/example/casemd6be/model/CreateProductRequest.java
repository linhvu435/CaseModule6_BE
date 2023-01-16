package com.example.casemd6be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private long id;
    private String name;
    private String detail;
    private Double price ;
    private long amount ;
    private List<String> imgs;
    private long categoryId;
    private long shopId;
}
