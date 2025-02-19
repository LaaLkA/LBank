package com.laalka.expensesservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для ответа о расходе.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {

    private Long id;
    private Long userId;

    @NotNull
    private Long receiverId;
    @NotNull
    private Double amount;

    private String category;
}
