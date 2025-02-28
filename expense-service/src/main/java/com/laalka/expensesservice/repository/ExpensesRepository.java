package com.laalka.expensesservice.repository;

import com.laalka.expensesservice.models.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpenseEntity, Long> {
//    ExpenseEntity findByReceiverHash(String receiverHash);
    List<ExpenseEntity> findExpenseBySender(String sender);
//    boolean existsByTransactionId(String transactionId);
    ExpenseEntity findById(UUID id);
}
