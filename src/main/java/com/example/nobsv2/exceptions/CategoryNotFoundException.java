package com.example.nobsv2.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryNotFoundException extends RuntimeException {

    private final static Logger logger = LoggerFactory.getLogger(CategoryNotFoundException.class);

    public CategoryNotFoundException() {
        super("Category Not Found (CategoryNotFoundException)");
        logger.error("EXCEPTION ALERT!!!!: {} thrown\n", getClass());
    }
}
