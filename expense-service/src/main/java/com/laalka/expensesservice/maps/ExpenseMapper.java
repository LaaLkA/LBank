package com.laalka.expensesservice.maps;

import com.laalka.expensesservice.dto.ExpenseRequest;
import com.laalka.expensesservice.dto.ExpenseResponse;
import com.laalka.expensesservice.models.ExpenseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    /**
     * Преобразуем DTO-запрос в сущность.
     */
    public ExpenseEntity fromRequestToEntity(ExpenseRequest request) {
        ExpenseEntity entity = new ExpenseEntity();
//        entity.setUserHash(request.getUserHash());
//        entity.setReceiverHash(request.getReceiverHash());
        entity.setSender(request.getSender());
        entity.setReceiver(request.getReceiver());
        entity.setAmount(request.getAmount());
//        entity.setCategory(request.getCategory());
        return entity;
    }
    /**
     * Преобразуем сущность в DTO-ответ.
     */
    public ExpenseResponse fromEntityToResponse(ExpenseEntity entity) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(entity.getId());
//        response.setUserHash(entity.getUserHash());
//        response.setReceiverHash(entity.getReceiverHash());
        response.setSender(entity.getSender());
        response.setReceiver(entity.getReceiver());
        response.setAmount(entity.getAmount());
//        response.setCategory(entity.getCategory());
        return response;
    }
}
