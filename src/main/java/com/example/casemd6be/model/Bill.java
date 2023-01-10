package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
    public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double totalprice;

    private LocalDateTime Date;

    @ManyToOne
    private BillStatus billStatus;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> product;

    @ManyToOne
    private Account account;

}
