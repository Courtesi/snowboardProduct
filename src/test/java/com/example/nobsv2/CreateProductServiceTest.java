package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.CreateProductService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        // Initializes mocks and injects them
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_product_when_create_product_service_then_return_created_product() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product A");
        product.setDescription("Product description testcaseproduct");
        product.setPrice(69.0);

        when(productRepository.save(product)).thenReturn(product);

        // When
        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        // Then
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product)), response);

        verify(productRepository, times(1)).save(product);
    }
}
