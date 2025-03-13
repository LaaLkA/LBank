package com.laalka.paymentservice.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * DTO для запроса перевода между двумя пользователями
 */
@Data
@AllArgsConstructor
public class TransferRequest {

    @NotNull
    private String sender;
    @NotNull
    private String receiver;
    @NotNull
    private Double amount;

}
