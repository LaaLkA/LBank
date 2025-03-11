package com.laalka.paymentservice.services;

import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.repositories.BalanceRepository;
import com.laalka.paymentservice.utils.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

//    public BalanceEntity findBalanceByHashUser(String userHash) {
//        return balanceRepository.findBalanceByHashUser(userHash);
//    }

    public BalanceEntity getBalance(String userName) {
        return balanceRepository.findBalanceByUserName(userName);
    }

    public BalanceEntity createBalance(String userName) {
        Double balanceCount = new Random().nextDouble(10000.0, 10000000000.0);
        BalanceEntity balance = new BalanceEntity();
        balance.setUserName(userName);
//        balance.setHashUser(userHash);
        balance.setBalance(balanceCount);
        return balanceRepository.save(balance);
    }
}
