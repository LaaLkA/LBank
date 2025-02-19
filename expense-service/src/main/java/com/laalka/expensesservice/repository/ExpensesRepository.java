package com.laalka.expensesservice.repository;

import com.laalka.expensesservice.models.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpenseEntity, Long> {
    ExpenseEntity findByReceiverId(Long receiverId);
    List<ExpenseEntity> findByUserId(Long userId);
    boolean existsByTransactionId(String transactionId);
}
