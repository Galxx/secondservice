package com.example.secondservice.controller;


import com.example.secondservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/listuser")
    public List<User> listUser(JwtAuthenticationToken principal) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + principal.getToken().getTokenValue());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(
//                        "http://localhost:3000/api/listuser",
                        "http://userservise:3000/api/listuser",
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<User>>() {
                        });

        return rateResponse.getBody();

    }

}
