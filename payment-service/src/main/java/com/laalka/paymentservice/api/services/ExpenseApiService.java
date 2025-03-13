package com.laalka.paymentservice.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class ExpenseApiService {

    private final RestClient restClient;

    public ExpenseApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void commitPayment(String sender, String receiver, Double amount, String token) {
        restClient
                .post()
                .uri("/expense/create")
                .header("Authorization", "Bearer " + token)
                .cookie("JWT_TOKEN", token)
                .body(Map.of(
                        "sender", sender,
                        "receiver", receiver,
                        "amount", amount))
                .retrieve()
                .body(String.class);

    }
}
