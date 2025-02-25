package com.laalka.paymentservice.services;

import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.repositories.BalanceRepository;
import com.laalka.paymentservice.utils.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    public BalanceEntity findBalanceByHashUser(String userHash) {
        return balanceRepository.findBalanceByHashUser(userHash);
    }

    public BalanceEntity createBalance(String userHash) {
        BalanceEntity balance = new BalanceEntity();
        balance.setHashUser(userHash);
        balance.setBalance(50.0);
        return balanceRepository.save(balance);
    }
}
