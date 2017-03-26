package com.pb.deposits.ServerSideApp.repository;

import com.pb.deposits.ServerSideApp.entity.Account;
import com.pb.deposits.ServerSideApp.entity.enums.TypeDeposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends PagingAndSortingRepository<Account, String> {

    //Get Sum all deposits
    @Query(value = "SELECT SUM(amount) FROM account", nativeQuery = true)
    Long getSumAllAccounts();

    //Get Count all deposits
    Long countBy();

    //Get List accounts by Depositor
    @Query(value = "SELECT * FROM account a INNER JOIN depositor d ON d.email = a.depositor WHERE d.email = ?1", nativeQuery = true)
    List<Account> findByDepositor(@Param("depositor") String email);

    //Get List accounts by TypeDeposit
    List<Account> findByTypeDeposit(@Param("typeDeposit") TypeDeposit typeDeposit);

    //Get List accounts by BankName
    List<Account> findByBankName(@Param("bankName") String bankName);
}

