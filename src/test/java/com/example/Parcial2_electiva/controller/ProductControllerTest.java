package com.example.Parcial2_electiva.controller;

import com.example.Parcial2_electiva.dto.ProductDTO;
import com.example.Parcial2_electiva.model.Product;
import com.example.Parcial2_electiva.service.ProductService;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    // Test para crear un producto
    @Test
    void createProduct_shouldReturnCreated() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(100);

        doNothing().when(productService).addProduct(any(ProductDTO.class));

        ResponseEntity<?> respuesta = productController.createProduct(productDTO);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertEquals("product created successfully.", respuesta.getBody());
    }

    // Test para obtener un producto por ID (caso exitoso)
    @Test
    void getProductById_shouldReturnProduct() throws Exception {
        when(productService.retrieveProductById(1L)).thenReturn(new Product("Test Product", "Test Description", 100));

        ResponseEntity<?> respuesta = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }

    // Test para obtener un producto por ID (producto no encontrado)
    @Test
    void getProductById_shouldReturnNotFound() throws Exception {
        when(productService.retrieveProductById(1L)).thenThrow(NoResultException.class);

        ResponseEntity<?> respuesta = productController.getProductById(1L);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        assertEquals("product not found.", respuesta.getBody());
    }

    // Test para obtener todos los productos
    @Test
    void getAllProducts_shouldReturnProducts() throws Exception {
        when(productService.retrieveAllProducts()).thenReturn(Collections.singletonList(new Product("Test Product", "Test Description", 100)));

        ResponseEntity<List<Product>> respuesta = productController.getAllProducts();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(1, respuesta.getBody().size());
    }

    // Test para actualizar un producto
    @Test
    void updateProduct_shouldReturnUpdated() throws Exception {
        Product product = new Product("Updated Product", "Updated Description", 200);
        product.setId(1L);

        doNothing().when(productService).updateProduct(any(Product.class));

        ResponseEntity<String> respuesta = productController.updateProduct(1L, product);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("product updated successfully.", respuesta.getBody());
    }

    // Test para actualizar un producto (error)
    @Test
    void updateProduct_shouldReturnInternalServerError() throws Exception {
        Product product = new Product("Updated Product", "Updated Description", 200);
        product.setId(1L);

        doThrow(new RuntimeException("error")).when(productService).updateProduct(any(Product.class));

        ResponseEntity<String> respuesta = productController.updateProduct(1L, product);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
        assertEquals("Failed to update product.", respuesta.getBody());
    }

    // Test para eliminar un producto
    @Test
    void deleteProduct_shouldReturnDeleted() throws Exception {
        Product product = new Product("Test Product", "Test Description", 100);
        product.setId(1L);

        doNothing().when(productService).deleteProduct(any(Product.class));
        when(productService.retrieveProductById(1L)).thenReturn(product);

        ResponseEntity<String> respuesta = productController.deleteProduct(1L);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("product deleted successfully.", respuesta.getBody());
    }

    // Test para eliminar un producto (producto no encontrado)
    @Test
    void deleteProduct_shouldReturnNotFound() throws Exception {
        when(productService.retrieveProductById(1L)).thenThrow(NoResultException.class);

        ResponseEntity<String> respuesta = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        assertEquals("product not found.", respuesta.getBody());
    }

    // Test para eliminar un producto (error)
    @Test
    void deleteProduct_shouldReturnInternalServerError() throws Exception {
        Product product = new Product("Test Product", "Test Description", 100);
        product.setId(1L);

        doThrow(new RuntimeException("error")).when(productService).deleteProduct(any(Product.class));

        ResponseEntity<String> respuesta = productController.deleteProduct(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
        assertEquals("Failed to delete product.", respuesta.getBody());
    }
}
