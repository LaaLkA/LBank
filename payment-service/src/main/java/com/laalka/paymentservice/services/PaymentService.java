package com.laalka.paymentservice.services;

import com.laalka.paymentservice.api.services.ExpenseApiService;
import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.repositories.BalanceRepository;
import com.laalka.paymentservice.utils.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private ExpenseApiService expenseApiService;

    @Autowired
    private HashService hashService;


    @Transactional
    public void transaction(String sender,
                            String receiver,
                            Double amount,
                            String token) {

        BalanceEntity senderBalance = balanceRepository.findBalanceByUserName(sender);
        if (senderBalance == null) {
            throw new RuntimeException("Sender not found");
        }

        BalanceEntity receiverBalance = balanceRepository.findBalanceByUserName(receiver);
        if (receiverBalance == null) {
            throw new RuntimeException("Receiver not found");
        }

        if (senderBalance.getBalance() < amount) {
            throw new RuntimeException("Sender doesn't have enough money");
        }

        senderBalance.setBalance(senderBalance.getBalance() - amount);
        receiverBalance.setBalance(receiverBalance.getBalance() + amount);
        balanceRepository.save(senderBalance);
        balanceRepository.save(receiverBalance);
        expenseApiService.commitPayment(sender,receiver, amount, token);

    }

}
