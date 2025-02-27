package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.BalanceRequest;
import com.laalka.webservice.api.services.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance")
public class BalanceApiController {
    private final BalanceService balanceService;

    public BalanceApiController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/get")
    public ResponseEntity<Double> getBalance(@RequestBody BalanceRequest req) {
        return ResponseEntity.ok(balanceService.getBalance(req.getUserName()).getBody());
    }
}
