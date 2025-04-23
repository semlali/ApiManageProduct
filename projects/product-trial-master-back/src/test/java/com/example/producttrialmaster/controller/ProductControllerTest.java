package com.example.producttrialmaster.controller;

import com.example.producttrialmaster.model.Product;
import com.example.producttrialmaster.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    public ProductControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = Arrays.asList(new Product(1L, "Product1"), new Product(2L, "Product2"));
        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        Product mockProduct = new Product(1L, "Product1");
        when(productService.getProductById(1L)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product1", response.getBody().getName());
        verify(productService, times(1)).getProductById(1L);
    }
}
