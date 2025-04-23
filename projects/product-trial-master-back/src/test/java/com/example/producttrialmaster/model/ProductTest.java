package com.example.producttrialmaster.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void testProductGettersAndSetters() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product1");

        assertEquals(1L, product.getId());
        assertEquals("Product1", product.getName());
    }

    @Test
    void testProductConstructor() {
        Product product = new Product(1L, "Product1");

        assertEquals(1L, product.getId());
        assertEquals("Product1", product.getName());
    }
}
