package com.example.casemd6be.repository.linh;

import com.example.casemd6be.model.ShopAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IShopAddressRepo extends PagingAndSortingRepository<ShopAddress,Long> {
    @Query(nativeQuery = true,value = "SELECT name from shop_address where id = :id")
    String findShopAddressNameByShopId(long id);


}
