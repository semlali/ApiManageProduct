package com.example.producttrialmaster.controller;

import com.example.producttrialmaster.model.Product;
import com.example.producttrialmaster.service.ProductService;
import com.example.producttrialmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    private UserService userService; // Injection du UserService pour vérifier le rôle ADMIN

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestHeader("Authorization") String token, @RequestBody Product product) {
        // Vérification du token JWT
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build(); // Non autorisé
        }
        String jwt = token.replace("Bearer ", "");
        if (!userService.isAdmin(jwt)) {
            return ResponseEntity.status(403).build(); // Accès interdit
        }
        try {
            return ResponseEntity.status(201).body(productService.createProduct(product));
        } catch (Exception e) {
            return ResponseEntity.status(400).build(); // Mauvaise requête en cas d'erreur
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody Product product) {
        // Vérification du token JWT
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build(); // Non autorisé
        }
        String jwt = token.replace("Bearer ", "");
        if (!userService.isAdmin(jwt)) {
            return ResponseEntity.status(403).build(); // Accès interdit
        }
        try {
            return ResponseEntity.ok(productService.updateProduct(id, product));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build(); // Mauvaise requête en cas d'erreur
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        // Vérification du token JWT
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build(); // Non autorisé
        }
        String jwt = token.replace("Bearer ", "");
        if (!userService.isAdmin(jwt)) {
            return ResponseEntity.status(403).build(); // Accès interdit
        }
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build(); // Mauvaise requête en cas d'erreur
        }
    }
}
