package com.laalka.webservice.api.services;

import com.laalka.webservice.api.dto.ExpenseResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class IncomeService {
    private final RestClient restClient;

    public IncomeService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<ExpenseResponse> getIncomes(String receiver, String token) {
        return restClient
                .get()
                .uri("/expense/userIncome/" + receiver)
                .cookie("JWT_TOKEN", token)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<ExpenseResponse>>() {})
                .getBody();
    }
}
