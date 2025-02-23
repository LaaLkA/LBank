package com.laalka.paymentservice.controllers;

import com.laalka.paymentservice.dto.BalanceRequest;
import com.laalka.paymentservice.dto.BalanceResponse;
import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.services.PaymentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final PaymentService paymentService;

    public BalanceController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<BalanceResponse> process(@NotNull @RequestBody BalanceRequest request) {
        BalanceEntity balance = paymentService.findBalanceByHashUser(
                request.getUserId(),
                request.getUserName(),
                request.getUserCreated()
        );

        if(balance == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Balance not found"
            );
        }
        return ResponseEntity.ok(new BalanceResponse(balance.getBalance()));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@NotNull @RequestParam String username) {

    }


}
