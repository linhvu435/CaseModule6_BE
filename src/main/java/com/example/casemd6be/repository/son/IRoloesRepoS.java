package com.example.casemd6be.repository.son;

import com.example.casemd6be.model.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoloesRepoS extends PagingAndSortingRepository<Roles,Long> {
    Iterable <Roles> findAll();
}

