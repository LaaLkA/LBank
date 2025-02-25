package com.laalka.authservice.api;

import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.repositories.AuthUserRepository;
import com.laalka.authservice.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BalanceService {
    private final JwtUtil jwtUtil;
    private final AuthUserRepository authUserRepository;

    public BalanceService(RestClient restClient, JwtUtil jwtUtil, AuthUserRepository authUserRepository) {
        this.restClient = restClient;
        this.jwtUtil = jwtUtil;
        this.authUserRepository = authUserRepository;
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

    public Double getBalance(String username) {
        String systemToken = jwtUtil.generateToken("systemUser", "ROLE_SYSTEM");
        AuthUser user = authUserRepository.findByUsername(username);
        return restClient
                .get()
                .uri("/payment/balance/get?userHash={userHash}", user.getUserHash())
                .header("Authorization", "Bearer " + systemToken)
                .retrieve()
                .body(Double.class);
    }
}
