package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Category category;

}
