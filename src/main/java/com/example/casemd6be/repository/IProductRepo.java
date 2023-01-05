package com.example.casemd6be.repository;

import com.example.casemd6be.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends PagingAndSortingRepository<Product,Long> {

}
