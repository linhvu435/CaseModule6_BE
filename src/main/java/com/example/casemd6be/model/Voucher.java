package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long amount;

    private long status;

    @OneToOne
    private Shop shop;

    @ManyToOne
    private  VoucherType voucherType;
}
