package com.example.Parcial2_electiva.service;

import com.example.Parcial2_electiva.dao.impl.ProductDaoImpl;
import com.example.Parcial2_electiva.model.Product;
import com.example.Parcial2_electiva.dto.ProductDTO;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDaoImpl productDaoImpl;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        // Inicia el objeto productService con dependencias mockeadas.
    }

    // Test para agregar un producto
    @Test
    void addProduct_shouldCallDaoSave() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(100);

        doNothing().when(productDaoImpl).save(any(Product.class));

        productService.addProduct(productDTO);

        verify(productDaoImpl, times(1)).save(any(Product.class));
    }

    // Test para actualizar un producto
    @Test
    void updateProduct_shouldCallDaoUpdate() {
        Product product = new Product("Updated Product", "Updated Description", 200);
        product.setId(1L);

        doNothing().when(productDaoImpl).update(any(Product.class));

        productService.updateProduct(product);

        verify(productDaoImpl, times(1)).update(any(Product.class));
    }

    // Test para eliminar un producto
    @Test
    void deleteProduct_shouldCallDaoDelete() {
        Product product = new Product("Test Product", "Test Description", 100);
        product.setId(1L);

        doNothing().when(productDaoImpl).delete(any(Product.class));

        productService.deleteProduct(product);

        verify(productDaoImpl, times(1)).delete(any(Product.class));
    }

    // Test para obtener todos los productos
    @Test
    void retrieveAllProducts_shouldReturnProducts() {
        when(productDaoImpl.getAll()).thenReturn(List.of(new Product("Test Product", "Test Description", 100)));

        List<Product> products = productService.retrieveAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
    }

    // Test para obtener un producto por ID (producto encontrado)
    @Test
    void retrieveProductById_shouldReturnProduct() {
        Product product = new Product("Test Product", "Test Description", 100);
        when(productDaoImpl.get(1L)).thenReturn(Optional.of(product));

        Product result = productService.retrieveProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
    }

    // Test para obtener un producto por ID (producto no encontrado)
    @Test
    void retrieveProductById_shouldThrowException() {
        when(productDaoImpl.get(1L)).thenReturn(Optional.empty());

        NoResultException exception = assertThrows(NoResultException.class, () -> {
            productService.retrieveProductById(1L);
        });

        assertEquals("No product found with id 1", exception.getMessage());
    }
}
