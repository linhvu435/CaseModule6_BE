package com.example.casemd6be.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialDTO {
    private String provider;
    private String   id;
    private String email;
    private String  name;
    private String  photoUrl;
    private String  firstName;
    private String  lastName;
    private String   idToken;
}