package com.laalka.paymentservice.repositories;

import com.laalka.paymentservice.models.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceRepository extends JpaRepository<BalanceEntity, UUID> {

    BalanceEntity findBalanceByUserName(String userName);
}
