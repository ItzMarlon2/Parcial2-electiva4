package com.example.Parcial2_electiva.dao.impl;

import com.example.Parcial2_electiva.model.Product;
import com.example.Parcial2_electiva.dao.Dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
public class ProductDaoImpl implements Dao<Product> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Product> get(Long id){
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    @Transactional
    public List<Product> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM product e");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public void update(Product product ) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        entityManager.remove(product);
    }
}
