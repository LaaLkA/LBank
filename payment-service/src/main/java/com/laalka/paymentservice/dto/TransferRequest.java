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
    private Long senderId;
    @NotNull
    private String senderName;
    @NotNull
    private LocalDateTime senderCreated;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private Long receiverId;
    @NotNull
    private String receiverName;
    @NotNull
    private LocalDateTime receiverCreated;

    public TransferRequest() {

    }
}
