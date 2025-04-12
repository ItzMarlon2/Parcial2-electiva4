package com.example.Parcial2_electiva.service;

import com.example.Parcial2_electiva.dto.ProductDTO;
import com.example.Parcial2_electiva.model.Product;
import com.example.Parcial2_electiva.dao.impl.ProductDaoImpl;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDaoImpl productDaoImpl;

    public void addProduct(ProductDTO product){
        try{
            Product newProduct = new Product(product.getName(), product.getDescription(), product.getPrice());
            productDaoImpl.save(newProduct);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product product){
        try{
            productDaoImpl.update(product);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(Product product){
        try{
            productDaoImpl.delete(product);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Product> retrieveAllProducts(){
        return productDaoImpl.getAll();
    }

    public Product retrieveProductById(Long id){
        Optional<Product> retrieveProduct = productDaoImpl.get(id);

        if(retrieveProduct.isPresent()){
            return retrieveProduct.get();
        }

        throw  new NoResultException("No product found with id " + id);
    }

}
