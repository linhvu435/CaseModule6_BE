package com.example.casemd6be.repository.sang;

import com.example.casemd6be.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MyShopRepo extends PagingAndSortingRepository<Shop, Long > {

    @Query(nativeQuery = true,value = "SELECT * from shop where id = :id")
    Shop findMyShopById(long id);
}
