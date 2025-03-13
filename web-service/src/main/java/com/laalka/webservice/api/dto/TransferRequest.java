package com.laalka.webservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequest {
    private String sender;
    private Double amount;
    private String receiver;
}

