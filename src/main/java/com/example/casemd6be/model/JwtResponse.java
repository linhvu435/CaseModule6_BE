package com.example.casemd6be.model;

import org.springframework.security.core.GrantedAuthority;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
//    private String type = "Bearer";
    private String username;
    private String email;
    private String img;
    private String phoneNumber;
    private String address;
    private String ShopAddress;

    public String getShopAddress() {
        return ShopAddress;
    }

    public void setShopAddress(String shopAddress) {
        ShopAddress = shopAddress;
    }

    private String gender;
    private LocalDate date;
    private LocalDate birthday;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String token, String username, String email, String img, String phoneNumber,String address,String ShopAddress,String gender,LocalDate date,LocalDate birthday,Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.img = img;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.ShopAddress = ShopAddress;
        this.gender = gender;
        this.date = date;
        this.birthday = birthday;
        this.roles = roles;
    }

    public JwtResponse(Long id, String token, String username, String email, String img, String phoneNumber, String address, String gender, LocalDate date, LocalDate birthday, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.img = img;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.date = date;
        this.birthday = birthday;
        this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setAvatar(String avatar) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setFullname(String fullname) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}
