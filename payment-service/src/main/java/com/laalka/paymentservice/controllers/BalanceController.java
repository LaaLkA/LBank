package com.laalka.paymentservice.controllers;

import com.laalka.paymentservice.dto.BalanceRequest;
import com.laalka.paymentservice.dto.BalanceResponse;
import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.services.BalanceService;
import com.laalka.paymentservice.services.PaymentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public ResponseEntity<BalanceResponse> process(@NotNull @RequestBody BalanceRequest request) {
        BalanceEntity balance = balanceService.findBalanceByHashUser(
                request.getUserHash()
        );

        if (balance == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Balance not found"
            );
        }
        return ResponseEntity.ok(new BalanceResponse(balance.getBalance()));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@NotNull @RequestParam String userHash) {
        balanceService.createBalance(userHash);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
