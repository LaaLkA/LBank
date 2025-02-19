package com.laalka.paymentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * DTO для отправки баланса пользователя
 */
@Data
@AllArgsConstructor
public class BalanceResponse {

    @NotNull
    private Double balance;

    public BalanceResponse() {
    }
}



