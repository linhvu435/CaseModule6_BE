package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.Trademark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrademarkRepoM extends PagingAndSortingRepository<Trademark,Long> {
    @Query(nativeQuery = true,value = "SELECT * from trademark where category_id = :category_id")
    List<Trademark> findTrademarksByCategory_Id(long category_id);
}
