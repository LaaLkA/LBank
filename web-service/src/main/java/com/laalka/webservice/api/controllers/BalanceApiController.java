package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.BalanceRequest;
import com.laalka.webservice.api.services.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class BalanceApiController {
    private final BalanceService balanceService;

    public BalanceApiController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/get")
    public ResponseEntity<Double> getBalance(
            @CookieValue(name = "USER_NAME", required = false) String userName,
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        System.out.println(userName);
        System.out.println(token);
        return balanceService.getBalance(userName, token);
    }
}
