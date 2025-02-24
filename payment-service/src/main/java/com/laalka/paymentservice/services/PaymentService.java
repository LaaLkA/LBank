package com.laalka.paymentservice.services;

import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.models.OutboxEvent;
import com.laalka.paymentservice.repositories.BalanceRepository;
import com.laalka.paymentservice.repositories.OutboxEventRepository;
import com.laalka.paymentservice.utils.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import com.laalka.events.PaymentEvent;

@Service
public class PaymentService {
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @Autowired
    private JsonService jsonService;

    @Autowired
    private HashService hashService;


    @Transactional
    public void transaction(String senderHash,
                            String receiverHash,
                            Double amount) {

        BalanceEntity senderBalance = balanceRepository.findBalanceByHashUser(senderHash);
        if (senderBalance == null) {
            throw new RuntimeException("Sender not found");
        }

        BalanceEntity receiverBalance = balanceRepository.findBalanceByHashUser(receiverHash);
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

        PaymentEvent event = new PaymentEvent(
                hashService.transactionHash(senderHash, receiverHash),
                senderHash,
                receiverHash,
                amount,
                LocalDateTime.now().toString()
        );

        String eventJson = jsonService.serializeToJson(event);

        OutboxEvent outboxEvent = OutboxEvent.builder()
                .eventType("PAYMENT_EVENT")
                .aggregateId(senderHash.toString())
                .payload(eventJson)
                .createdAt(LocalDateTime.now())
                .processed(false)
                .build();

        outboxEventRepository.save(outboxEvent);
    }

}
