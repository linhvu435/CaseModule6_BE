package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cmt;

    private long star;
    @ManyToOne
    private Product product;

    @ManyToOne
    private Account account;
}
