package com.example.casemd6be.repository.son;



import com.example.casemd6be.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepoS extends PagingAndSortingRepository<Account, Long> {


    @Query(nativeQuery = true,value = "SELECT * from account where username = :username and phone_number= :phoneNumber")
    Account findAccountByPhoneNumberAndUsername(@Param("username") String username, @Param("phoneNumber") String phoneNumber);

    @Query(nativeQuery = true,value = "SELECT img from account where username = :username")
    String accountImg(@Param("username") String username);

    @Query(nativeQuery = true,value = "SELECT * from account where username = :username")
    String profile(@Param("username") String username);

    @Query(nativeQuery = true,value = "SELECT * from account where phone_number = :phone_number")
    List<Account> findByAccountPhoneNumber(@Param("phone_number") String phone_number);

    @Query(nativeQuery = true,value = "SELECT * from account where roles_id = :id")
    List<Account>  findByIdRoles(Long id);

    @Query(nativeQuery = true,value = "SELECT * from account where username like concat('%',:username,'%')")
    List<Account> findAccountByUsername(@Param("username") String username);

    @Query(nativeQuery = true,value = "SELECT * from account where username = :username")
    Account findByUsername (String username);


    @Query(nativeQuery = true,value = "SELECT * from account where id = :id")
    Account findByIdUsername (Long id);


}
