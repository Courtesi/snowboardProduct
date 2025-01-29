package com.example.nobsv2.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfanityApiNotWorkingException extends RuntimeException {

    private final static Logger logger = LoggerFactory.getLogger(ProfanityApiNotWorkingException.class);

    public ProfanityApiNotWorkingException() {
        super("Profanity API is Currently Not Working (ProfanityApiNotWorkingException)");
        logger.error("EXCEPTION ALERT!!!!: {} thrown\n", getClass());
    }
}