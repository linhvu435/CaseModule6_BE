package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.ImgProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImgProductRepoM extends PagingAndSortingRepository<ImgProduct,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM img_product where product_id = :product_id")
    List<ImgProduct> findAllImgByProduct(long product_id);


}
