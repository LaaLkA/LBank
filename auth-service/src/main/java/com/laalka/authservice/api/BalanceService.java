package com.laalka.authservice.api;

import com.laalka.authservice.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BalanceService {
    private final JwtUtil jwtUtil;

    public BalanceService(RestClient restClient, JwtUtil jwtUtil) {
        this.restClient = restClient;
        this.jwtUtil = jwtUtil;
    }

    private final RestClient restClient;

    public void createBalance(String userHash) {
        String systemToken = jwtUtil.generateToken("systemUser", "ROLE_SYSTEM");
        try {
            restClient
                    .post()
                    .uri("/payment/balance/create?userHash={userHash}", userHash)
                    .header("Authorization", "Bearer " + systemToken)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Profile creation failed: " + e.getMessage(), e);
        }
    }
}
