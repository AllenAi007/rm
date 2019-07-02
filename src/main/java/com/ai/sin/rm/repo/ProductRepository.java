package com.ai.sin.rm.repo;

import com.ai.sin.rm.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Product CRUD Repository
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    /**
     * Find all the product where it is less than or equal to a given risk
     * @param risk - a given risk
     * @return set of product
     */
    @Query("select p from Product p where p.risk <= ?1")
    Set<Product> findByRiskLessThanEqual(int risk);
}
