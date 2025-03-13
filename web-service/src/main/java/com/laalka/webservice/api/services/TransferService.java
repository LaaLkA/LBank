package com.laalka.webservice.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class TransferService {
    private final RestClient restClient;


    public TransferService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<String> doTransfer(String sender, String receiver, Double amount, String token) {
        return restClient
                .post()
                .uri("/payment/transfer")
                .cookie("JWT_TOKEN", token)
                .body(Map.of(
                        "sender", sender,
                        "receiver", receiver,
                        "amount", amount))
                .retrieve()
                .toEntity(String.class);
    }
}
