package com.example.nobsv2.exceptions;


public class ProductNotValidException extends RuntimeException {
    public ProductNotValidException(String message) {
        super(message);
    }
}
