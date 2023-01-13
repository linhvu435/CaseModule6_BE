package com.example.casemd6be.repository;



import com.example.casemd6be.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepo extends CrudRepository<Account, Long> {


    @Query(nativeQuery = true,value = "SELECT * from account where username = :username and phone_number= :phoneNumber")
    Account findAccountByPhoneNumberAndUsername(@Param("username") String username, @Param("phoneNumber") String phoneNumber);

    @Query(nativeQuery = true,value = "SELECT img from account where username = :username")
    String accountImg(@Param("username") String username);

    @Query(nativeQuery = true,value = "SELECT * from account where username = :username")
    String profile(@Param("username") String username);

    @Query(nativeQuery = true,value = "SELECT * from account where phone_number = :phoneNumber")
    Account findByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true,value = "SELECT * from account where username = :username")
    Account findByUsername( String username);

    Account findAccountById(long id);

    @Query(nativeQuery = true,value = "SELECT * from account where email = :email")
    List<Account> findAccountByEmail(String email);

}
