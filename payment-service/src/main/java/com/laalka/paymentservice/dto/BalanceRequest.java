package com.laalka.paymentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;

/**
 * DTO для запроса баланса пользователя
 */
@Data
@AllArgsConstructor
public class BalanceRequest {

    @NotNull
    private Long userId;
    @NotNull
    private String userName;
    @NotNull
    private LocalDateTime userCreated;

    public BalanceRequest() {
    }
}
