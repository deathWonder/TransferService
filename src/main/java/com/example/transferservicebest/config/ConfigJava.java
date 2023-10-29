package com.example.transferservicebest.config;

import com.example.transferservicebest.repository.TransferRepositoryImpl;
import com.example.transferservicebest.service.Validation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigJava {

    @Bean
    public Validation validation(TransferRepositoryImpl repository) {
        return new Validation(repository);
    }
}
