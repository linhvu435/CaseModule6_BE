package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long status;

    @OneToOne
    private Bill bill;
    @ManyToOne
    private  OrderDetails orderDetails;
}
