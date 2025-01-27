package com.example.nobsv2;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        // Initializes mocks and injects them
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_products_exist_when_get_products_service_return_product_dtos() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product A");
        product1.setDescription("Description for Product A");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product B");
        product2.setDescription("Description for Product B");
        product2.setPrice(20.0);

        List<Product> products = List.of(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        //When
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Product A", response.getBody().get(0).getName()); // Example check
        assertEquals("Product B", response.getBody().get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void given_no_products_when_get_products_service_return_empty_list() {
        // Given
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty()); // Checks that the list is empty
        verify(productRepository, times(1)).findAll();
    }
}
