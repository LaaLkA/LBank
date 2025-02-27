package com.laalka.webservice.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthService {

    private final RestClient restClient;

    public AuthService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String login(String userName, String password) {
        try {
            return restClient
                    .post()
                    .uri("/auth/authorization/login?userName={userName}&password={password}", userName, password)
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
                    .uri("/auth/authorization/register?userName={userName}&password={password}", userName, password)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Register failed: " + e.getMessage(), e);
        }
    }

}

