package com.pb.deposits.ServerSideApp.repository;

import com.pb.deposits.ServerSideApp.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends PagingAndSortingRepository<Account, String> {

    List<Account> findByName(@Param("name") String name);

}

