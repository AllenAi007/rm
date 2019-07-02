package com.ai.sin.rm.repo;

import com.ai.sin.rm.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for client repository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UTClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * test find all
     */
    @Test
    public void testFindAll() {
        entityManager.persist(new Client("test1", 1));
        entityManager.persist(new Client("test2", 2));
        entityManager.persist(new Client("test3", 3));
        Set<Client> result = clientRepository.findAll();
        assertEquals(3, result.size());
    }
}
