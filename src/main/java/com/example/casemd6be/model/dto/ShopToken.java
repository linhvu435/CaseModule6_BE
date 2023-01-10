package com.example.casemd6be.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopToken {
    private long id;
    private String img;
    private String name;
    private String address;

}
