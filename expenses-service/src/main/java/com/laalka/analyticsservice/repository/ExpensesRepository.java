package com.laalka.analyticsservice.repository;

import com.laalka.analyticsservice.models.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<ExpenseEntity, Long> {
    ExpenseEntity findByReceiver(String receiver);
}
