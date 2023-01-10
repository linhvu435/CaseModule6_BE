package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.Bill;
import com.example.casemd6be.model.BillStatus;
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
    List<Bill> findAllBbyIdAccount(long account_id);

    @Query(nativeQuery = true, value = "SELECT * from bill join product on product.shop_id = :shop_id order by date desc")
    List<Bill> findAllByShop_IdDesc(long shop_id);


    @Query(nativeQuery = true, value = "SELECT * FROM casemd6.bill where id = :idbill")
    Bill findBillById(long idbill);

    @Query(nativeQuery = true, value = "SELECT * FROM casemd6.bill where bill_status_id = :id")
    List<Bill> showbillbystatus(long id);





}
