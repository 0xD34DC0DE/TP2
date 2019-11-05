package com.fabeme.tp2backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.fabeme.tp2backend.repository")
@SpringBootApplication
public class Tp2backendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp2backendApplication.class, args);
    }
}
