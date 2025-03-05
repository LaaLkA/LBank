package com.laalka.webservice.api.services;

import com.laalka.webservice.api.dto.ProfileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProfileService {

    private final RestClient restClient;

    public ProfileService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ProfileResponse getProfile(String userName, String token) {
        try {
            return restClient
                    .get()
                    .uri("/user/profile/" + userName)
                    .cookie("JWT_TOKEN", token)
                    .retrieve()
                    .body(ProfileResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении профиля для пользователя " + userName, e);
        }
    }

}
