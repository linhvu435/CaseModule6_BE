package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private LocalDate birthday;

    private LocalDate date;
    private String address;
    private String gender;
    private String img;
    private long status;
    @ManyToOne
    private Roles roles;
}
