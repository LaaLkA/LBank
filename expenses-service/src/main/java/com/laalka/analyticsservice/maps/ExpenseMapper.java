package com.laalka.analyticsservice.maps;

import com.laalka.analyticsservice.dto.ExpenseRequest;
import com.laalka.analyticsservice.dto.ExpenseResponse;
import com.laalka.analyticsservice.models.ExpenseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    /**
     * Преобразуем DTO-запрос в сущность.
     */
    public ExpenseEntity fromRequestToEntity(ExpenseRequest request) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setUserId(request.getUserId());
        entity.setReceiverName(request.getReceiverName());
        entity.setAmount(request.getAmount());
        entity.setCategory(request.getCategory());
        return entity;
    }
    /**
     * Преобразуем сущность в DTO-ответ.
     */
    public ExpenseResponse fromEntityToResponse(ExpenseEntity entity) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(entity.getId());
        response.setUserId(entity.getUserId());
        response.setReceiverName(entity.getReceiverName());
        response.setAmount(entity.getAmount());
        response.setCategory(entity.getCategory());
        return response;
    }
}
