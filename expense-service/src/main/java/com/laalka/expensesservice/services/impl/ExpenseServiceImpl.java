package com.laalka.expensesservice.services.impl;

import com.laalka.expensesservice.models.ExpenseEntity;
import com.laalka.expensesservice.repository.ExpensesRepository;
import com.laalka.expensesservice.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpensesRepository expensesRepository;

    @Autowired
    public ExpenseServiceImpl(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @Override
    public List<ExpenseEntity> expensesList(String sender) {
        return expensesRepository.findExpenseBySender(sender);
    }


    @Override
    public void createExpense(ExpenseEntity expense) {
        expensesRepository.save(expense);
    }

    @Override
    public ExpenseEntity updateExpense(UUID expenseId,
                                       String sender,
                                       String receiver,
                                       Double amount,
                                       String category) {
        Optional<ExpenseEntity> optionalExpense = expensesRepository.findById(expenseId);
        if (optionalExpense.isEmpty()) {
            throw new RuntimeException("Expense with id " + expenseId + " not found");
        }

        ExpenseEntity existingExpense = optionalExpense.get();

        existingExpense.setAmount(existingExpense.getAmount());
        existingExpense.setCategory(existingExpense.getCategory());

        return expensesRepository.save(existingExpense);
    }

}
