package com.example.casemd6be.repository.son;

import com.example.casemd6be.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepoS extends PagingAndSortingRepository<Product, Long> {
    @Query(nativeQuery = true,value = "SELECT * from product join category on product.id = category.id where category.id = :id")
    List<Product> findByIdCategory(Long id);


    Optional<Product> findById(Long id);

    @Query(nativeQuery = true,value = "SELECT * from product where id = :id")
    Product findProductById(long id);






}
