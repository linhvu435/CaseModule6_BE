package com.example.casemd6be.repository.linh;

import com.example.casemd6be.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepo extends PagingAndSortingRepository<Comment,Long> {
}
