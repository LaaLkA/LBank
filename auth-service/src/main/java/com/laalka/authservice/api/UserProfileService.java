package com.laalka.authservice.api;

import com.laalka.authservice.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserProfileService {

    private final RestClient restClient;
    private final JwtUtil jwtUtil;

    public UserProfileService(RestClient restClient, JwtUtil jwtUtil) {
        this.restClient = restClient;
        this.jwtUtil = jwtUtil;
    }

    public void createProfile(String userHash, String userName) {
        String systemToken = jwtUtil.generateToken("systemUser", "ROLE_SYSTEM");
        try {
            restClient
                    .post()
                    .uri("/user/profile/create?userHash={userHash}&userName={userName}", userHash, userName)
                    .header("Authorization", "Bearer " + systemToken)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Profile creation failed: " + e.getMessage(), e);
        }
    }

}
