package com.example.nobsv2.snowboard.models;

public enum Regions {
    US("United States of America"),
    CAN("Canada");

    private final String message;

    Regions(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
