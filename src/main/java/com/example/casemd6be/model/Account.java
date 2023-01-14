package com.example.casemd6be.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(unique = true, nullable = false)
//    @NotBlank(message = "user name không được để trống")
    private String username;

//    @Column(unique = true, nullable = false)
//    @Email
//    @NotBlank(message = "email không để trống")
    private String email;

//    @Column(nullable = false)
//    @NotBlank(message = "password không được để trống")
    private String password;

//    @Column(nullable = false)
//    @NotBlank(message = "số điện thoại không được để trống")
    private String phoneNumber;
    private String name;

//    @Past(message = "Ngày sinh phải trước thời gian hiện tại")
//    @NotNull(message = "không được để trống")
    private LocalDate birthday;
    private LocalDate date;

//    @Column(nullable = false)
//    @NotBlank(message = "địa chỉ không được để trống")
    private String address;
    private String gender;
    private String img;
    private long status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Roles roles;
}
