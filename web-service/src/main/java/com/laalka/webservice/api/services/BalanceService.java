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

    public ResponseEntity<Double> getBalance(String userName) {
        return restClient
                .post()
                .uri("/payment/balance/get")
                .body(Map.of(
                        "userName", userName))
                .retrieve()
                .toEntity(Double.class);
    }
}
