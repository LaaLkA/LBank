package com.laalka.expensesservice.services;

import com.laalka.expensesservice.models.ExpenseEntity;

import java.util.List;

public interface ExpenseService {
    List<ExpenseEntity> expensesList(Long userId);
    ExpenseEntity createExpense(Long userId, Long receiverId, Double amount);
    ExpenseEntity createExpense(Long userId, Long receiverId, Double amount, String category);
    ExpenseEntity updateExpense(Long expenseId, Long userId, Long receiverId, Double amount, String category);
    void deleteExpense(Long expenseId);
}
