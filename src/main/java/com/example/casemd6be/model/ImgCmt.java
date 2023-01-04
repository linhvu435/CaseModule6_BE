package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImgCmt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Comment comment;
}
