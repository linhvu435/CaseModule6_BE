package com.example.casemd6be.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Roles implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    @Override
    public String getAuthority() {
        return name;
    }

}
