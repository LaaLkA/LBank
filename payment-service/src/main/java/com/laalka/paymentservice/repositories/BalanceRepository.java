package com.laalka.paymentservice.repositories;

import com.laalka.paymentservice.models.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
    BalanceEntity findBalanceByHashUser(String hashUser);
}
