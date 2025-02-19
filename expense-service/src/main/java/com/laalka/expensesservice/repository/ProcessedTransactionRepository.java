package com.laalka.expensesservice.repository;

import com.laalka.expensesservice.models.ProcessedTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedTransactionRepository extends JpaRepository<ProcessedTransactionEntity, Long> {
    boolean existsByTransactionId(String transactionId);
}