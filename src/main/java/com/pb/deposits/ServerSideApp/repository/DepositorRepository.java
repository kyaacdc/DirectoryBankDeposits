package com.pb.deposits.ServerSideApp.repository;

import com.pb.deposits.ServerSideApp.entity.Depositor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "depositor", path = "depositor")
public interface DepositorRepository extends PagingAndSortingRepository<Depositor, String> {

    //Remove depositor
    void delete(@Param("email") String email);

    //Get Depositors by Name
    List<Depositor> findByName(@Param("name") String name);
}
