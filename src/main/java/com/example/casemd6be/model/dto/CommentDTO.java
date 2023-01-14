package com.example.casemd6be.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    String content;
    long star;
    long productId;
}
