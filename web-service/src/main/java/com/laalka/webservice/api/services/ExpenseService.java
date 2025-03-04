package com.laalka.webservice.api.services;

import com.laalka.webservice.api.dto.ExpenseResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExpenseService {

    private final RestClient restClient;

    public ExpenseService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<ExpenseResponse> getExpenses(String sender, String token) {
        return restClient
                .get()
                .uri("/expense/user/" + sender)
                .cookie("JWT_TOKEN", token)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<ExpenseResponse>>() {})
                .getBody();
    }
}
