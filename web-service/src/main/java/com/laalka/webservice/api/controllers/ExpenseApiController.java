package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.ExpenseResponse;
import com.laalka.webservice.api.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseApiController {

    private final ExpenseService expenseService;

    public ExpenseApiController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ExpenseResponse>> getUserExpenses(
            @RequestParam String sender,
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        List<ExpenseResponse> expenses = expenseService.getExpenses(sender, token);
        System.out.println("!!!!!!!0000000!!!!!!!0000000!!!!!!!0000000    " + sender);
        return ResponseEntity.ok(expenses);
    }
}
