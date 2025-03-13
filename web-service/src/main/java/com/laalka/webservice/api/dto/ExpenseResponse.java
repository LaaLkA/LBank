package com.laalka.webservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ExpenseResponse {
    private UUID id;
    private String sender;
    private String receiver;
    private Double amount;

}
