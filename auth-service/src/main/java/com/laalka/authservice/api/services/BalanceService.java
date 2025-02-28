package com.laalka.authservice.api.services;

import com.laalka.authservice.repositories.AuthUserRepository;
import com.laalka.authservice.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class BalanceService {

    private final JwtUtil jwtUtil;

    public BalanceService(RestClient restClient, JwtUtil jwtUtil, AuthUserRepository authUserRepository) {
        this.restClient = restClient;
        this.jwtUtil = jwtUtil;
    }

    private final RestClient restClient;

    public void createBalance(String userName) {
        String systemToken = jwtUtil.generateToken("systemUser", "ROLE_SYSTEM");
        try {
            restClient
                    .post()
                    .uri("/payment/balance/create")
                    .header("Authorization", "Bearer " + systemToken)
                    .body(Map.of(
                            "userName", userName))
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Profile creation failed: " + e.getMessage(), e);
        }
    }

}
