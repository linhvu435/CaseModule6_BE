package com.example.casemd6be.repository.linh;

import com.example.casemd6be.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICommentRepo extends PagingAndSortingRepository<Comment,Long> {
    @Query(nativeQuery = true,value = "select*from comment where product_id = :id ")
    Iterable<Comment> findAllCommentByIdProduct(long id);
}
