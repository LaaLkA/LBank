package com.laalka.expensesservice.services;

import com.laalka.expensesservice.models.ExpenseEntity;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    List<ExpenseEntity> expensesList(String userName);

    List<ExpenseEntity> incomesList(String receiver);

    void createExpense(ExpenseEntity expense);
    ExpenseEntity updateExpense(UUID expenseId,
                                String sender,
                                String receiver,
                                Double amount,
                                String category);

}
