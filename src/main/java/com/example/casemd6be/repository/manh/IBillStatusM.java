package com.example.casemd6be.repository.manh;

import com.example.casemd6be.model.BillStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillStatusM extends PagingAndSortingRepository<BillStatus,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM casemd6.bill_status where id =:idstatus")
    BillStatus findBillStatusById(long idstatus);
    @Query(nativeQuery = true, value = "SELECT * FROM casemd6.bill_status")
    List<BillStatus> findAllBillStatus();
}
