package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IProductRepoM extends PagingAndSortingRepository<Product,Long> {
    @Query(nativeQuery = true,value = "SELECT * from product where shop_id = :shop_id")
    List<Product> findProductByShopId(long shop_id);

    @Query(nativeQuery = true,value = "SELECT * from product where category_id = :category_id")
    List<Product> findProductByCategory_Id(long category_id);

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name LIKE concat('%',:name,'%')")
    List<Product> findByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * FROM product ")
    List<Product> findAllP();

    @Query(nativeQuery = true, value = "SELECT * FROM product order by amountsell desc ")
    List<Product> findAllTopProduct();



    @Query(nativeQuery = true,value = "SELECT * FROM product WHERE name LIKE concat('%',:name,'%') AND category_id = :category_id")
    List<Product> findProductByCategory_IdAndName(@Param("name") String name ,@Param("category_id") long category_id );
}
