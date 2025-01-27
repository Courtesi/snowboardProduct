package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.UpdateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_update_product_service_then_return_updated_product() {
        // Given
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Product Description");
        updatedProduct.setPrice(15.0);

        Product existingProduct = new Product();
        existingProduct.setId(1);
        existingProduct.setName("Old Product");
        existingProduct.setDescription("Old Description");
        existingProduct.setPrice(10.0);

        UpdateProductCommand command = new UpdateProductCommand(1, updatedProduct);

        when(productRepository.findById(command.getId())).thenReturn(Optional.of(existingProduct));

        // When
        ResponseEntity<ProductDTO> response = updateProductService.execute(command);

        // Then
        assertEquals(ResponseEntity.ok(new ProductDTO(updatedProduct)), response);
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    public void given_product_not_found_when_update_product_service_then_throw_product_not_found_exception() {
        // Given
        Product existingProduct = new Product();
        existingProduct.setId(1);
        existingProduct.setName("Old Product");
        existingProduct.setDescription("Old Description");
        existingProduct.setPrice(10.0);

        UpdateProductCommand command = new UpdateProductCommand(1, existingProduct);

        // When & Then
        assertThrows(ProductNotFoundException.class, () -> updateProductService.execute(command));
        verify(productRepository, times(1)).findById(1); // Verify findById was called once
        verify(productRepository, times(0)).save(any()); // Ensure save was never called
    }
}