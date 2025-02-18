package com.laalka.analyticsservice.services;

import com.laalka.analyticsservice.models.ExpenseEntity;

import java.util.List;

public interface ExpenseService {
    List<ExpenseEntity> expensesList(Long userId);
    ExpenseEntity createExpense(Long userId, String receiverName, Double amount);
    ExpenseEntity createExpense(Long userId, String receiverName, Double amount, String category);
    ExpenseEntity updateExpense(Long expenseId, Long userId, String receiverName, Double amount, String category);
    void deleteExpense(Long expenseId);
}
