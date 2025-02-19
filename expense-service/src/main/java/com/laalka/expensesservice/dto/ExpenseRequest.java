package com.laalka.expensesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

/**
 * DTO для создания/обновления расхода (запрос).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    @NotNull
    private Long userId;

    private Long receiverId;
    private Double amount;
    private String category;

}
