package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.Bill;
import com.example.casemd6be.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepoM extends PagingAndSortingRepository<Bill,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM bill ")
    List<Bill> findAllB();

    @Query(nativeQuery = true, value = "SELECT * FROM bill where account_id = :account_id")
    List<Bill> findAllB(long account_id);





}
