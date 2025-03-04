package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.ExpenseResponse;
import com.laalka.webservice.api.services.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeApiController {

    private final IncomeService incomeService;

    public IncomeApiController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ExpenseResponse>> getUserExpenses(
            @RequestParam String receiver,
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        List<ExpenseResponse> expenses = incomeService.getIncomes(receiver, token);
        System.out.println("!!!!!!!0000000!!!!!!!0000000!!!!!!!0000000    " + receiver);
        return ResponseEntity.ok(expenses);
    }
}
