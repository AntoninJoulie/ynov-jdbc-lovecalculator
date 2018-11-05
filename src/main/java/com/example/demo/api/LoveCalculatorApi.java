package com.example.demo.api;

import com.example.demo.configuration.ApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoveCalculatorApi {

    @Autowired
    private ApiConfiguration apiConfiguration;

    @Autowired
    private RestTemplate restTemplate;

    public LoveResult bringMeLove(String firstName, String secondName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set(apiConfiguration.getHeader(), apiConfiguration.getKey());

        ResponseEntity<LoveResult> response = restTemplate.exchange(
                apiConfiguration.buildUrl(firstName, secondName),
                HttpMethod.GET,
                new HttpEntity<>("parameters", headers),
                LoveResult.class
        );

        return response.getBody();
    }

}
