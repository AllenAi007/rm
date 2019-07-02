package com.ai.sin.rm.repo;

import com.ai.sin.rm.model.Client;
import com.ai.sin.rm.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for client repository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UTProductRepository {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByRiskLessThanEqual() {
        entityManager.persist(new Product("test1", 1, 3));
        entityManager.persist(new Product("test2", 2, 3));
        entityManager.persist(new Product("test3", 3, 3));
        Set<Product> result = productRepository.findByRiskLessThanEqual(2);
        assertEquals(2, result.size());
    }
}
