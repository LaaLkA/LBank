package com.laalka.analyticsservice.services.impl;

import com.laalka.analyticsservice.models.ExpenseEntity;
import com.laalka.analyticsservice.repository.ExpensesRepository;
import com.laalka.analyticsservice.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpensesRepository expensesRepository;

    @Autowired
    public ExpenseServiceImpl(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @Override
    public List<ExpenseEntity> expensesList(Long userId) {
        return expensesRepository.findByUserId(userId);
    }

    @Override
    public ExpenseEntity createExpense(Long userId, String receiverName, Double amount) {
        return expensesRepository.save(new ExpenseEntity(userId, receiverName, amount, "No defined"));
    }

    @Override
    public ExpenseEntity createExpense(Long userId, String receiverName, Double amount, String category) {
        return expensesRepository.save(new ExpenseEntity(userId, receiverName, amount, category));
    }

    @Override
    public ExpenseEntity updateExpense(Long expenseId, Long userId, String receiverName, Double amount, String category) {
        Optional<ExpenseEntity> optionalExpense = expensesRepository.findById(expenseId);
        if (optionalExpense.isEmpty()) {
            throw new RuntimeException("Expense with id " + expenseId + " not found");
        }

        ExpenseEntity existingExpense = optionalExpense.get();

        existingExpense.setUserId(existingExpense.getUserId());
        existingExpense.setReceiverName(existingExpense.getReceiverName());
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
