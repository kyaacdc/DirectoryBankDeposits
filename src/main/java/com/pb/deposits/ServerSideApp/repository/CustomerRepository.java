package com.pb.deposits.ServerSideApp.repository;

import com.pb.deposits.ServerSideApp.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {

    List<Customer> findByBankName(@Param("bankName") String bankName);

}
