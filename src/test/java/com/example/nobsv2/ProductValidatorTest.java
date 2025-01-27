package com.example.nobsv2;

import com.example.nobsv2.exceptions.ErrorMessages;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.validators.ProductValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

public class ProductValidatorTest {

    @Test
    public void given_name_is_empty_when_validate_product_then_throw_name_required_exception() {
        // Given
        Product product = new Product();
        product.setName(""); // Empty name
        product.setDescription("Description of Product A");
        product.setPrice(10.0);

        // When & Then
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
        assertEquals(ErrorMessages.NAME_REQUIRED.getMessage(), exception.getMessage());
    }

    @Test
    public void given_description_is_too_short_when_validate_product_then_throw_description_length_exception() {
        // Given
        Product product = new Product();
        product.setName("Product A");
        product.setDescription("Short"); // Description less than 20 characters
        product.setPrice(10.0);

        // When & Then
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
        assertEquals(ErrorMessages.DESCRIPTION_LENGTH.getMessage(), exception.getMessage());
    }

    @Test
    public void given_price_is_negative_when_validate_product_then_throw_price_negative_exception() {
        // Given
        Product product = new Product();
        product.setName("Product A");
        product.setDescription("Description of Product A");
        product.setPrice(-1.0); // Negative price

        // When & Then
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
        assertEquals(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage(), exception.getMessage());
    }

    @Test
    public void given_valid_product_when_validate_product_then_do_not_throw_exception() {
        // Given
        Product product = new Product();
        product.setName("Product A");
        product.setDescription("Description of Product A");
        product.setPrice(10.0); // Valid product

        // When & Then
        assertDoesNotThrow(() -> ProductValidator.execute(product));
    }
}