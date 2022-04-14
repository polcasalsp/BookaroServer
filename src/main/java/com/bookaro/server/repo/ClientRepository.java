package com.bookaro.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bookaro.server.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
