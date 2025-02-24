package com.laalka.webservice.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProfileService {

    private final GatewayClientService gatewayClientService;
    private final RestClient restClient;

    public ProfileService(GatewayClientService gatewayClientService, RestClient restClient) {
        this.gatewayClientService = gatewayClientService;
        this.restClient = restClient;
    }

    public String getUserName(String jwtToken) {
        ResponseEntity<String> response = restClient
                .get()
                .uri("/user/userName")
                .header("Authorization", "Bearer " + jwtToken)
                .retrieve()
                .body(String.class);
        return response.getBody();
    }
}
