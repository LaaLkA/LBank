package com.laalka.webservice.services;

import com.laalka.webservice.dto.WebUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProfileService {

    private final RestClient restClient;

    public ProfileService(GatewayClientService gatewayClientService, RestClient restClient) {
        this.restClient = restClient;
    }

    public String getUserName(String token) {
        WebUser user = getWebUser(token);
        return user.getUsername();
    }

    private WebUser getWebUser(String jwtToken) {
        return restClient
                .get()
                .uri("/auth/user/me")
                .header("Authorization", "Bearer " + jwtToken)
                .retrieve()
                .body(WebUser.class);
    }
}
