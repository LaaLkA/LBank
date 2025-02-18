package com.laalka.analyticsservice.dto;

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
    @NotNull
    private Long userId;

    private String receiverName;
    private Double amount;
    private String category;

}
