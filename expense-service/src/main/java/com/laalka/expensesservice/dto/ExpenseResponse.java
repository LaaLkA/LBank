package com.laalka.expensesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO для ответа о расходе.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {

    private UUID id;
    private String sender;
    private String receiver;
    private Double amount;

}
