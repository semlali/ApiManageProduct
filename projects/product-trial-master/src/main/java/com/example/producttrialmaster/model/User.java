package com.example.producttrialmaster.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") // Renomme la table pour éviter les conflits avec le mot réservé "user"
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstname;
    private String email;
    private String password;

    private String role; // Ajout du champ rôle (ex: "ADMIN" ou "USER")

    @OneToMany
    private List<Product> cart = new ArrayList<>();

    @OneToMany
    private List<Product> wishlist = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getCart() {
        return cart;
    }

    public List<Product> getWishlist() {
        return wishlist;
    }

    // Getters et setters
}
