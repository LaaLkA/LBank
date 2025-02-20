package com.laalka.webservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GatewayClientService {

    private final RestTemplate restTemplate;

    // URL Gateway (или eureka-lb://api-gateway)
    private final String gatewayUrl = "http://api-gateway:8089";

    public GatewayClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // auth-service -> /auth/login
    public String login(String username, String password) {
        // POST http://api-gateway:8080/auth/login?username=xxx&password=yyy
        String url = gatewayUrl + "/auth/login?username=" + username + "&password=" + password;
        try {
            return restTemplate.postForObject(url, null, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    // auth-service -> /auth/register
    public void register(String username, String password) {
        String url = gatewayUrl + "/auth/register?username=" + username + "&password=" + password;
        restTemplate.postForObject(url, null, String.class);
    }

    // Можно добавить другие вызовы, например changePassword, resetPassword, etc.
}

