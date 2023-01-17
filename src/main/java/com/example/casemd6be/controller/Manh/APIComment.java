package com.example.casemd6be.controller.Manh;

import com.example.casemd6be.model.Account;
import com.example.casemd6be.model.Comment;
import com.example.casemd6be.model.Product;
import com.example.casemd6be.model.dto.CommentDTO;
import com.example.casemd6be.repository.IAccountRepo;
import com.example.casemd6be.repository.linh.ICommentRepo;
import com.example.casemd6be.repository.manh.IProductRepoM;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetTimeKeyDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/comment")
public class APIComment {
    @Autowired
    ICommentRepo iCommentRepo;

    @Autowired
    IProductRepoM iProductRepoM;
    @Autowired
    IAccountRepo iAccountRepo;
    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CommentDTO comment){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountRepo.findByUsername(userDetails.getUsername());
        Product product = iProductRepoM.findById(comment.getProductId()).get();
        Comment comment1 =new Comment();
        comment1.setAccount(account);
        comment1.setStar(comment.getStar());
        comment1.setProduct(product);
        comment1.setCmt(comment.getContent());
        iCommentRepo.save(comment1);
        return ResponseEntity.ok(comment1);
    }
    @GetMapping("{id}")
    public Iterable<Comment> findCmtById(@PathVariable long id){
        return  iCommentRepo.findAllCommentByIdProduct(id);
    }
}
