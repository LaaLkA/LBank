package com.laalka.authservice.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserProfileService {

    private final RestClient restClient;

    public UserProfileService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void createProfile(String userHash, String userName) {
        try {
            restClient
                    .post()
                    .uri("/user/profile/create?userHash={userHash}&username={userName}", userHash, userName)
                    .retrieve()
                    .body(String.class);
        }catch (Exception e) {
            throw new RuntimeException("Profile creation failed: " + e.getMessage(), e);
        }
    }

}
