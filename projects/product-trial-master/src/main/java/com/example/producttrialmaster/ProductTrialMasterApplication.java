package com.example.producttrialmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.producttrialmaster") // Vérifie que tous les sous-packages sont scannés
//@EnableWebSecurity
public class ProductTrialMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductTrialMasterApplication.class, args);
    }

}
