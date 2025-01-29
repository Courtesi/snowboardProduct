package com.example.nobsv2.snowboard.validators;

import com.example.nobsv2.exceptions.HasProfanityException;
import com.example.nobsv2.snowboard.models.NoProfanityResponse;
import com.example.nobsv2.snowboard.models.Snowboard;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ProfanityChecker {

    private ProfanityChecker() {
    }

    public static void execute(Snowboard snowboard, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();

        String text = "text";
        String noProfanityUrl = "https://api.api-ninjas.com/v1/profanityfilter";
        URI uri = UriComponentsBuilder
                .fromHttpUrl(noProfanityUrl)
                .queryParam(text, snowboard.getDescription())
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<NoProfanityResponse> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    NoProfanityResponse.class
            );

            if (response.getBody().isHas_profanity()) {
                throw new HasProfanityException();
            }
        } catch (Exception e) {
            if (e.getClass() == HasProfanityException.class) {
                throw new HasProfanityException();
            }

            throw e;
//            throw new ProfanityApiNotWorkingException();
        }
    }
}
