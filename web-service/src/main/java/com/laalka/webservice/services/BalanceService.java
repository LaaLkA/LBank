package com.laalka.webservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BalanceService {
    private final RestClient restClient;
    private final ProfileService profileService;

    public BalanceService(RestClient restClient, ProfileService profileService) {
        this.restClient = restClient;
        this.profileService = profileService;
    }

    public Double getBalance(String token) {
        String userName = profileService.getUserName(token);
        return restClient
                .get()
                .uri("/auth/balance?userName={userName}", userName)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(Double.class);
    }
}
