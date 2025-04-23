package com.example.producttrialmaster.controller;

import com.example.producttrialmaster.model.User;
import com.example.producttrialmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/account")
    public ResponseEntity<User> createAccount(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createAccount(user));
    }

    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        Optional<String> token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return token.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }
}
