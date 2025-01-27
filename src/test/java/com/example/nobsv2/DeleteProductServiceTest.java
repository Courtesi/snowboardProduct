package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.DeleteProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_delete_product_service_then_return_no_content() {
        // Given
        Integer productId = 1;
        Product product = new Product();
        product.setId(productId);
        product.setName("Product A");
        product.setDescription("Description of Product A");
        product.setPrice(10.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<Void> response = deleteProductService.execute(productId);

        // Then
        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), response);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void given_product_not_found_when_delete_product_service_then_throw_product_not_found_exception() {
        // Given
        Integer productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(productId));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).deleteById(productId); // Ensure deleteById is not called
    }
}