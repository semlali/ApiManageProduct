package com.example.producttrialmaster.service;

import com.example.producttrialmaster.model.Product;
import com.example.producttrialmaster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setCode(updatedProduct.getCode());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setImage(updatedProduct.getImage());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setInternalReference(updatedProduct.getInternalReference());
            product.setShellId(updatedProduct.getShellId());
            product.setInventoryStatus(updatedProduct.getInventoryStatus());
            product.setRating(updatedProduct.getRating());
            product.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public UserService getUserService() {
        // Assuming you have a UserService bean defined in your application context
        // You can inject it here or use ApplicationContext to get it
        return new UserService(); // Replace with actual UserService bean retrieval
    }

}