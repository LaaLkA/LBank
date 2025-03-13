package com.laalka.webservice.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class BalanceService {
    private final RestClient restClient;

    public BalanceService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<Double> getBalance(String userName, String token) {
        return restClient
                .post()
                .uri("/payment/balance/get")
                .header("Authorization", "Bearer " + token)
                .body(Map.of(
                        "userName", userName))
                .retrieve()
                .toEntity(Double.class);
    }
}
