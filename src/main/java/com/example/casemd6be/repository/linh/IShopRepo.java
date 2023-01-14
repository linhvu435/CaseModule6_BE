package com.example.casemd6be.repository.linh;

import com.example.casemd6be.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IShopRepo extends PagingAndSortingRepository<Shop,Long> {
    @Query(nativeQuery = true,value = "SELECT * from shop where account_id = :id")
    Shop findShopByAccountId(long id);
    @Query(nativeQuery = true,value = "SELECT shop_address_id from shop where account_id = :id")
    Long findIdShopAddressByIdAccount(long id);

    @Query(nativeQuery = true,value = "SELECT * from shop where account_id = :id")
    Shop findShopAddressNameByShopId(long id);
}
