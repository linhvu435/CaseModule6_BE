package com.example.casemd6be.repository.manh;


import com.example.casemd6be.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IShopRepoM extends PagingAndSortingRepository<Shop,Long> {
    @Query(nativeQuery = true,value = "SELECT * from shop where shop_address_id = :shop_address_id")
    List<Shop> findShopsByShopAddress(long shop_address_id);

    @Query(nativeQuery = true,value = "SELECT * from shop where account_id =:account_id")
    Shop findShopByAccountId(long account_id);

    @Query(nativeQuery = true,value = "SELECT * from shop")
    List<Shop> findAllShop();
}
