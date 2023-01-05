package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double totalprice;

    private LocalDate Date;

    private long status;

    @ManyToMany
    private List<Product> product;

    @ManyToOne
    private Account account;
}
