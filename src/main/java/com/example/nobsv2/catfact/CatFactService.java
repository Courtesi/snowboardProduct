package com.example.nobsv2.catfact;

import com.example.nobsv2.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

    private final RestTemplate restTemplate;
    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENGTH = "max_length";

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        //sets up our URL with Query string parameter
        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam(MAX_LENGTH, input)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json"); //can abstract these strings to top of class

        HttpEntity<String> entity = new HttpEntity<>(headers);

        //handle cat fact error response
        try {
            ResponseEntity<CatFactResponse> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    CatFactResponse.class
            );

            CatFactDTO catFactDTO = new CatFactDTO(response.getBody().getFact());
            return ResponseEntity.ok(catFactDTO);

        } catch(Exception exception) {
            //can throw your own custom exception here
            throw new RuntimeException("Cat Facts API is down");
        }

        //get for object is a GET request
//        CatFactResponse response = restTemplate.getForObject(
//                "?max_length=" + input,
//                CatFactResponse.class
//        );


    }
}
