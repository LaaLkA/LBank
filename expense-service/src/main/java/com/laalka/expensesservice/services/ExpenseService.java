package com.laalka.expensesservice.services;

import com.laalka.expensesservice.models.ExpenseEntity;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    List<ExpenseEntity> expensesList(String userHash);
    ExpenseEntity createExpense(String userHash, String receiverHash, Double amount);
    ExpenseEntity createExpense(String userHash, String receiverHash, Double amount, String category);
    ExpenseEntity updateExpense(UUID expenseId, String userHash, String receiverHash, Double amount, String category);
    void deleteExpense(Long expenseId);
}
