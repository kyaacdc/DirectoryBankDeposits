package com.pb.deposits.ServerSideApp.repository;

import com.pb.deposits.ServerSideApp.entity.Depositor;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "depositor", path = "depositor")
public interface DepositorRepository extends Repository<Depositor, String> {

    //Add new account
    Depositor save(Depositor depositor);

    //Delete account
    void delete(@Param("email") String email);

    List<Depositor> findByName(@Param("name") String name);



}
