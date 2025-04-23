package com.example.producttrialmaster.service;

import com.example.producttrialmaster.model.Product;
import com.example.producttrialmaster.model.User;
import com.example.producttrialmaster.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecretString; // Charge la clé secrète depuis application.properties

    private SecretKeySpec getJwtSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecretString);
        return new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode le mot de passe
        return userRepository.save(user);
    }

    public Optional<String> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.get().getEmail())
                    .claim("role", user.get().getRole()) // Ajout du rôle dans les claims
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
                    .signWith(getJwtSecretKey()) // Utilise la clé secrète correcte
                    .compact();
            return Optional.of(token);
        }
        return Optional.empty();
    }

    public boolean isAdmin(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getJwtSecretKey()) // Utilise la clé secrète correcte
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return "ADMIN".equals(claims.get("role")); // Vérifie si le rôle est ADMIN
        } catch (MalformedJwtException | IllegalArgumentException e) {
            return false; // Retourne false si le token est mal formé ou invalide
        }
    }

    public void addToCart(Long userId, Product product) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getCart().add(product);
        userRepository.save(user);
    }

    public void addToWishlist(Long userId, Product product) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getWishlist().add(product);
        userRepository.save(user);
    }
}
