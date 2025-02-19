package com.laalka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEvent {
    private String transactionId;
    private Long senderId;
    private Long receiverId;
    private Double amount;
    private String timestamp;
}