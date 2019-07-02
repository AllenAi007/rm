package com.ai.sin.rm.service.imp;

import com.ai.sin.rm.exception.ClientNotFoundException;
import com.ai.sin.rm.model.Client;
import com.ai.sin.rm.repo.ClientRepository;
import com.ai.sin.rm.repo.ProductRepository;
import com.ai.sin.rm.service.RelationshipManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Relationship Manager service implementation
 */
@Service
@Transactional(readOnly = true)
public class RelationshipManagerServiceImp implements RelationshipManagerService {

    private static final Logger LOG = LoggerFactory.getLogger(RelationshipManagerServiceImp.class);

    private ClientRepository clientRepository;

    private ProductRepository productRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<Client> getClientRecommendations() {
        Set<Client> clients = clientRepository.findAll();
        if (clients == null || clients.isEmpty()) {
            throw new ClientNotFoundException("No client found exception");
        }
        return clients.stream().map(this::setClientRecommendations).collect(Collectors.toSet());
    }

    @Override
    public Client getClientRecommendations(Integer clientId) {
        Client client = clientRepository.findOne(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return setClientRecommendations(client);
    }

    /**
     * Set the client recommendations
     * @param client a given client
     * @return a client with recommendations
     */
    private Client setClientRecommendations(Client client) {
        client.setProducts(productRepository.findByRiskLessThanEqual(client.getRisk()));
        return client;
    }

}
