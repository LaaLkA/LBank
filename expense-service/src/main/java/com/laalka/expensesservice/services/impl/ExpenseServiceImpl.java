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
    public List<ExpenseEntity> expensesList(String userHash) {
        return expensesRepository.findByUserHash(userHash);
    }

    @Override
    public ExpenseEntity createExpense(String userHash, String receiverHash, Double amount) {
        return expensesRepository.save(new ExpenseEntity(userHash, receiverHash, amount, "No defined"));
    }

    @Override
    public ExpenseEntity createExpense(String userHash, String receiverHash, Double amount, String category) {
        return expensesRepository.save(new ExpenseEntity(userHash, receiverHash, amount, category));
    }

    @Override
    public ExpenseEntity updateExpense(UUID expenseId, String userHash, String receiverHash, Double amount, String category) {
        Optional<ExpenseEntity> optionalExpense = Optional.ofNullable(expensesRepository.findById(expenseId));
        if (optionalExpense.isEmpty()) {
            throw new RuntimeException("Expense with id " + expenseId + " not found");
        }

        ExpenseEntity existingExpense = optionalExpense.get();

        existingExpense.setUserHash(existingExpense.getUserHash());
        existingExpense.setReceiverHash(existingExpense.getReceiverHash());
        existingExpense.setAmount(existingExpense.getAmount());
        existingExpense.setCategory(existingExpense.getCategory());

        return expensesRepository.save(existingExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        if (!expensesRepository.existsById(expenseId)) {
            throw new RuntimeException("Expense with id " + expenseId + " not found");
        }
        expensesRepository.deleteById(expenseId);
    }
}
