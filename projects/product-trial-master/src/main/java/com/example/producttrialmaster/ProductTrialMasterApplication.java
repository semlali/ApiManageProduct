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

/*    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/auth/**", "/products/**", "/error").permitAll() // Autorise l'accès à /auth/**
            .anyRequest().authenticated(); // Bloque les autres routes
        return http.build();
    }
}

@RestController
class CustomErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError() {
        return "Une erreur s'est produite. Veuillez vérifier vos permissions ou contacter l'administrateur.";
    }*/
}
