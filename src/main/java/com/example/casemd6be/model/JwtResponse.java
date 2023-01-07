package com.example.casemd6be.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
//    private String type = "Bearer";
    private String username;
    private String email;
    private String img;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String token, String username, String email, String img, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.img = img;
        this.roles = roles;
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
