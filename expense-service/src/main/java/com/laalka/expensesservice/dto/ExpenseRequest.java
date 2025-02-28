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

    private String sender;
    private String receiver;
    private Double amount;

}
