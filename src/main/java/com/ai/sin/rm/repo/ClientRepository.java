package com.ai.sin.rm.repo;

import com.ai.sin.rm.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Client CRUD Repository
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {

    /**
     * Find all the clients
     * @return set of clients
     */
    public Set<Client> findAll();

}
