package com.example.nobsv2.snowboard.models;

import lombok.Data;

@Data
public class NoProfanityResponse {
    private String original;
    private String censored;
    private boolean has_profanity;
}
