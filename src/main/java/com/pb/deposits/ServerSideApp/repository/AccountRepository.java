package com.pb.deposits.ServerSideApp.repository;

import com.pb.deposits.ServerSideApp.entity.Account;
import com.pb.deposits.ServerSideApp.entity.Depositor;
import com.pb.deposits.ServerSideApp.entity.enums.TypeDeposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends Repository<Account, String> {

    //Get List all deposits
    List<Account> findAll();

    //Get Sum all deposits
    @Query(value = "SELECT SUM(amount) FROM account", nativeQuery = true)
    long getSumAllAccounts();

    //Get count all deposits
    long count();

    //Get info by account
    Account findOne(String id);

    //Get List accounts by Depositor
    List<Account> findByDepositor(Depositor depositor);

    //Get List accounts by TypeDeposit
    List<Account> findByTypeDeposit(TypeDeposit typeDeposit);

    //Get List accounts by BankName
    List<Account> findByBankName(@Param("bankName") String bankName);

    //Add new account
    Account save(Account account);

    //Delete account
    void delete(@Param("id") String id);
}

