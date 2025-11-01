package com.example.demo.config;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(CustomerRepository repo) {
        return args -> {
            repo.save(new Customer("Aravind", "aravind@example.com", 26));
            repo.save(new Customer("Sebastian", "sebastian@example.com", 30));
        };
    }
}
