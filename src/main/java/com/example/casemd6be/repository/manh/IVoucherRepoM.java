package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.Voucher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IVoucherRepoM extends PagingAndSortingRepository<Voucher,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM voucher where name = :name")
    Voucher findVoucherByName(String name);


    @Query(nativeQuery = true, value = "SELECT * FROM voucher where shop_id = :shop_id")
    List<Voucher> findAllByShop_ID(long shop_id);
}
