package com.example.currencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.currencies.repository")
@EntityScan(basePackages = "com.example.currencies.model")
public class CurrenciesApplication {
    public static void main(String[] args){
        SpringApplication.run(CurrenciesApplication.class, args);
    }
}
