package com.laalka.expensesservice.services;

import com.laalka.expensesservice.models.ExpenseEntity;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    List<ExpenseEntity> expensesList(String userName);
//    ExpenseEntity createExpense(String userHash, String receiverHash, Double amount);
    void createExpense(ExpenseEntity expense);
    ExpenseEntity updateExpense(UUID expenseId, String userHash, String receiverHash, Double amount, String category);
    void deleteExpense(Long expenseId);
}
