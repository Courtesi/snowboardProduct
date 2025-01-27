package com.example.nobsv2;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    //Beans get injected into spring container
    //will cover this in dependency injection
    //bean gives us access to rest template throughout the app
    public RestTemplate restTemplate() {
        //configure your rest template options
        return new RestTemplate();
    }
}
