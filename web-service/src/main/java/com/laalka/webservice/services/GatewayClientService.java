package com.laalka.webservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GatewayClientService {

    private final RestClient restClient;

    public GatewayClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String login(String userName, String password) {
        try {
            return restClient
                    .post()
                    .uri("/auth/login?userName={userName}&password={password}", userName, password)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    public void register(String userName, String password) {
        try {
            restClient
                    .post()
                    .uri("/auth/register?userName={userName}&password={password}", userName, password)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Register failed: " + e.getMessage(), e);
        }
    }

    public String getUserProfile(String token) {
        return restClient
                .get()
                .uri("/user/")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(String.class);
    }
}

