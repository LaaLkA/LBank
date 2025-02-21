package com.laalka.webservice.services;

import com.laalka.webservice.config.WebConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Service
public class GatewayClientService {

    private final RestClient restClient;


    public GatewayClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String login(String username, String password) {
        try {
            return restClient
                    .post()
                    .uri("/auth/login&username={username}&password={password}", username, password)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    public void register(String username, String password) {
        try {
            restClient
                    .post()
                    .uri("/auth/register?username={username}&password={password}", username, password)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Register failed: " + e.getMessage(), e);
        }
    }

}

