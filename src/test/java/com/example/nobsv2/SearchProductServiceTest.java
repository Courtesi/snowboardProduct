package com.example.nobsv2;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.SearchProductService;
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

public class SearchProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_found_when_search_product_service_then_return_product_list() {
        // Given
        String searchTerm = "Product";
        Product product1 = new Product();
        product1.setId(1); // Using integer ID
        product1.setName("Product A");
        product1.setDescription("Description of Product A");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2); // Using integer ID
        product2.setName("Product B");
        product2.setDescription("Description of Product B");
        product2.setPrice(15.0);

        List<Product> products = List.of(product1, product2);

        when(productRepository.findByNameOrDescriptionContaining(searchTerm)).thenReturn(products);

        // When
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute(searchTerm);

        // Then
        assertEquals(ResponseEntity.ok(List.of(new ProductDTO(product1), new ProductDTO(product2))), response);
        verify(productRepository, times(1)).findByNameOrDescriptionContaining(searchTerm);
    }

    @Test
    public void given_no_products_found_when_search_product_service_then_return_empty_list() {
        // Given
        String searchTerm = "NonExistentProduct";
        when(productRepository.findByNameOrDescriptionContaining(searchTerm)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<ProductDTO>> response = searchProductService.execute(searchTerm);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(productRepository, times(1)).findByNameOrDescriptionContaining(searchTerm);
    }
}
