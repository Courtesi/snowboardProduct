package com.example.nobsv2.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found test (ErrorMessages)"),
    NAME_REQUIRED("Name is required test (ErrorMessages)"),
    DESCRIPTION_LENGTH("Description must be at least 20 characters test (ErrorMessages)"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be negative test (ErrorMessages)");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
