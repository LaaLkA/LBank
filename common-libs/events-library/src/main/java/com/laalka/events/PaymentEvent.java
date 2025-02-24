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
    private String senderHash;
    private String receiverHash;
    private Double amount;
    private String timestamp;
}