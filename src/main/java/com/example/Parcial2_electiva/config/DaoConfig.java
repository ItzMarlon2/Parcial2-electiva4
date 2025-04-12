package com.example.Parcial2_electiva.config;

import com.example.Parcial2_electiva.dao.impl.ProductDaoImpl;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public ProductDaoImpl productDaoImpl(EntityManager entityManager){
        return new ProductDaoImpl(entityManager);
    }
}
