package com.example.casemd6be.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long status;
    private String img;
    private String phone;

    private LocalDate date;

    @OneToOne
    private ShopAddress shopAddress;
    @OneToOne
    private Account account;


}
