package com.example.nobsv2.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HasProfanityException extends RuntimeException {

    private final static Logger logger = LoggerFactory.getLogger(HasProfanityException.class);

    public HasProfanityException() {
        super("Found Profanity in Description (HasProfanityException)");
        logger.error("EXCEPTION ALERT!!!!: {} thrown\n", getClass());
    }
}
