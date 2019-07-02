package com.ai.sin.rm.service;

import com.ai.sin.rm.model.Client;

import java.util.Set;

/**
 * Relationship Manager service
 */
public interface RelationshipManagerService {

    /**
     * Get all the recommendations for all the clients
     *
     * @return Set of client with recommendations
     * @throws com.ai.sin.rm.exception.ClientNotFoundException
     */
    public Set<Client> getClientRecommendations();


    /**
     * Get all the recommendations for a given clients
     *
     * @param clientId - given a client id
     * @return a Client with recommendations
     * @throws com.ai.sin.rm.exception.ClientNotFoundException
     */
    public Client getClientRecommendations(Integer clientId);

}
