package com.ai.sin.rm.service;

import com.ai.sin.rm.exception.ClientNotFoundException;
import com.ai.sin.rm.model.Client;
import com.ai.sin.rm.model.Product;
import com.ai.sin.rm.repo.ClientRepository;
import com.ai.sin.rm.repo.ProductRepository;
import com.ai.sin.rm.service.imp.RelationshipManagerServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


/**
 * Unit test for service, using mockito to reduce the dependency on repositories
 */
@RunWith(SpringRunner.class)
public class UTRelationshipManagerService {

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private RelationshipManagerService relationshipManagerService;

    @TestConfiguration
    static class RelationshipManagerServiceImpTestConfiguration {
        @Bean
        public RelationshipManagerService relationshipManagerService() {
            return new RelationshipManagerServiceImp();
        }
    }

    @Before
    public void before() {
        Set<Client> clientSet = new HashSet<>();
        clientSet.add(new Client(1, "ALLEN", 3));
        clientSet.add(new Client(2, "AI", 4));
        clientSet.add(new Client(3, "HUA", 5));
        given(clientRepository.findAll()).willReturn(clientSet);

        when(clientRepository.findOne(any(Integer.class))).thenAnswer(invocation -> {
            int clientId = invocation.getArgumentAt(0, Integer.class);
            for (Client client : clientSet) {
                if (client.getId() == clientId) {
                    return client;
                }
            }
            return null;
        });


        Set<Product> products = new HashSet<>();
        products.add(new Product("SG BONDS", 2, 2));
        products.add(new Product("CHINA BONDS", 3, 4));
        products.add(new Product("THAILAND BONDS", 5, 4));
        products.add(new Product("SOMALIA BONDS", 8, 9));

        when(productRepository.findByRiskLessThanEqual(any(Integer.class))).thenAnswer(i -> {
            return products
                    .stream()
                    .filter(product -> product.getRisk() <= i.getArgumentAt(0, Integer.class))
                    .collect(Collectors.toSet());
        });
    }

    @Test
    public void testGetClientRecommendations() {
        Set<Client> clientsWithRecommendations = this.relationshipManagerService.getClientRecommendations();
        assertThat(clientsWithRecommendations).isNotEmpty();
        assertThat(clientsWithRecommendations.size()).isEqualTo(3);
        clientsWithRecommendations.stream().forEach(client -> {
            // ALLEN
            if (client.getId() == 1) {
                assertThat(client.getProducts().size()).isEqualTo(2);
            }
            // AI
            if (client.getId() == 2) {
                assertThat(client.getProducts().size()).isEqualTo(2);
            }
            //HUA
            if (client.getId() == 3) {
                assertThat(client.getProducts().size()).isEqualTo(3);
            }
        });
    }

    @Test
    public void testGetClientRecommendationsByClientId() {
        assertThat(this.relationshipManagerService.getClientRecommendations(1).getProducts().size()).isEqualTo(2);
        assertThat(this.relationshipManagerService.getClientRecommendations(2).getProducts().size()).isEqualTo(2);
        assertThat(this.relationshipManagerService.getClientRecommendations(3).getProducts().size()).isEqualTo(3);
    }

    @Test
    public void testGetClientRecommendationsWithNotFoundException() {
        given(clientRepository.findAll()).willReturn(null);
        String expectedErrorMessage = "No client found exception";
        assertThatThrownBy(() -> {
            this.relationshipManagerService.getClientRecommendations();
        }).isInstanceOf(ClientNotFoundException.class).withFailMessage(expectedErrorMessage);

        given(clientRepository.findAll()).willReturn(Collections.emptySet());

        assertThatThrownBy(() -> {
            this.relationshipManagerService.getClientRecommendations();
        }).isInstanceOf(ClientNotFoundException.class).withFailMessage(expectedErrorMessage);

    }

    @Test
    public void testGetClientRecommendationsByClientWithNotFoundException() {
        Integer clientId = 10;
        String expectedErrorMessage = "Client id " + clientId + " does not exist.";
        assertThatThrownBy(() -> {
            this.relationshipManagerService.getClientRecommendations(clientId);
        }).isInstanceOf(ClientNotFoundException.class).withFailMessage(expectedErrorMessage);

    }
}