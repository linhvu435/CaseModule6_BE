package com.example.casemd6be.model.dto;

import com.example.casemd6be.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ProductInBillDTO {
    long id;
    String name;

    List<Product> products ;
    Double total;




}
