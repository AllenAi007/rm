package com.ai.sin.rm.model;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test Product POJO
 */
public class UTProduct {

    @Test
    public void testProductEquals() {
        Product product = new Product();
        product.setId(1);
        product.setName("SG BONDS");
        product.setRisk(3);
        product.setInvestment(5);
        assertFalse(product.equals(null));
        assertTrue(product.equals(product));

        Product sameProduct = new Product();
        sameProduct.setId(1);
        sameProduct.setName("SG BONDS");
        sameProduct.setRisk(3);
        sameProduct.setInvestment(5);
        assertTrue(product.equals(sameProduct));

        Product anotherProduct = new Product();
        anotherProduct.setId(2);
        anotherProduct.setName("Other");
        anotherProduct.setRisk(3);
        anotherProduct.setInvestment(5);
        assertFalse(product.equals(anotherProduct));

    }

    @Test
    public void testHashCode() {
        Product product = new Product();
        product.setId(1);
        product.setName("SG BONDS");
        product.setInvestment(5);
        product.setRisk(3);
        assertNotNull(product.hashCode());

        Product sameProduct = new Product();
        sameProduct.setId(1);
        sameProduct.setName("SG BONDS");
        sameProduct.setInvestment(5);
        sameProduct.setRisk(3);
        assertTrue(product.hashCode() == sameProduct.hashCode());

        Product anotherProduct = new Product();
        anotherProduct.setId(2);
        anotherProduct.setName("Other");
        anotherProduct.setInvestment(5);
        anotherProduct.setRisk(3);
        assertFalse(product.hashCode() == anotherProduct.hashCode());
    }
}
