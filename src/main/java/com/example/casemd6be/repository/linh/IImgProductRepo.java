package com.example.casemd6be.repository.linh;

import com.example.casemd6be.model.ImgProduct;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface IImgProductRepo extends PagingAndSortingRepository<ImgProduct,Long> {
}
