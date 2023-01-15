package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.Comment;
import com.example.casemd6be.model.ImgProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepoM extends PagingAndSortingRepository<Comment,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM comment where product_id = :product_id")
    List<Comment> findAllCommentByP_ID(long product_id);


}