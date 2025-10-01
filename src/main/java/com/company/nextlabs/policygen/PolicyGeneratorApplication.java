package com.company.nextlabs.policygen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PolicyGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolicyGeneratorApplication.class, args);
        System.out.println("Spring Boot started successfully!");
    }
}