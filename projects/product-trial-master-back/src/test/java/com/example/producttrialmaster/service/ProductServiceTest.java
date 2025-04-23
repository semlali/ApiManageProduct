package com.example.producttrialmaster.service;

import com.example.producttrialmaster.model.Product;
import com.example.producttrialmaster.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_WhenProductExists() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.getProductById(productId);

        // Assert
        assertNotNull(result);
        assertEquals(productId, result.get().getId());
        assertEquals("Test Product", result.get().getName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetProductById_WhenProductDoesNotExist() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        Optional<Product> result = productService.getProductById(productId);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).findById(productId);
    }

    // Ajoutez d'autres tests ici pour couvrir les autres méthodes de ProductService
}
