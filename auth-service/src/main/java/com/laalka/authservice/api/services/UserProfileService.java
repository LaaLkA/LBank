package com.laalka.authservice.api.services;

import com.laalka.authservice.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class UserProfileService {

    private final RestClient restClient;
    private final JwtUtil jwtUtil;

    public UserProfileService(RestClient restClient, JwtUtil jwtUtil) {
        this.restClient = restClient;
        this.jwtUtil = jwtUtil;
    }

    public void createProfile(String userName) {
        String systemToken = jwtUtil.generateToken("systemUser", "ROLE_SYSTEM");
        try {
            restClient
                    .post()
                    .uri("/user/profile/create")
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
