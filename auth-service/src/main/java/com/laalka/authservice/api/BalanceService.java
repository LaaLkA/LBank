package com.laalka.authservice.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BalanceService {
    public BalanceService(RestClient restClient) {
        this.restClient = restClient;
    }

    private final RestClient restClient;

    public void createBalance(String username) {
        try {
            restClient
                    .post()
                    .uri("/payment/balance/create?username={username}", username)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Profile creation failed: " + e.getMessage(), e);
        }
    }
}
