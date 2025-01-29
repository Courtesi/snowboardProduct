package com.example.nobsv2.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends RuntimeException{

    private final static Logger logger = LoggerFactory.getLogger(ProductNotFoundException.class);

    public ProductNotFoundException() {
        super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        logger.error("EXCEPTION!!!!!: " + getClass() + " thrown");
    }
}
