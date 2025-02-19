package com.laalka.paymentservice.services;

import com.laalka.paymentservice.models.BalanceEntity;
import com.laalka.paymentservice.models.OutboxEvent;
import com.laalka.paymentservice.repositories.BalanceRepository;
import com.laalka.paymentservice.repositories.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import com.laalka.events.PaymentEvent;

@Service
public class PaymentService {
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserHashService userHashService;

    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @Autowired
    private JsonService jsonService;

    public BalanceEntity findBalanceByHashUser(Long userId, String userName, LocalDateTime userCreated) {
        return balanceRepository.findBalanceByHashUser(
                userHashService.userHash(userId, userName, userCreated));
    }

    @Transactional
    public void transaction(Long senderId,
                            String senderUserName,
                            LocalDateTime senderUserCreated,
                            Double amount,
                            Long receiverId,
                            String receiverUserName,
                            LocalDateTime receiverUserCreated) {

        String transactionId = userHashService.userHash(senderId, senderUserName, senderUserCreated);

        BalanceEntity senderBalance = balanceRepository.findBalanceByHashUser(
                userHashService.userHash(senderId, senderUserName, senderUserCreated));
        if (senderBalance == null) {
            throw new RuntimeException("Sender not found");
        }

        BalanceEntity receiverBalance = balanceRepository.findBalanceByHashUser(
                userHashService.userHash(receiverId, receiverUserName, receiverUserCreated));
        if (receiverBalance == null) {
            throw new RuntimeException("Receiver not found");
        }

        if(senderBalance.getBalance() < amount) {
            throw new RuntimeException("Sender doesn't have enough money");
        }

        senderBalance.setBalance(senderBalance.getBalance() - amount);
        receiverBalance.setBalance(receiverBalance.getBalance() + amount);
        balanceRepository.save(senderBalance);
        balanceRepository.save(receiverBalance);

        PaymentEvent event = new PaymentEvent(
                transactionId,
                senderId,
                receiverId,
                amount,
                LocalDateTime.now().toString()
        );

        String eventJson = jsonService.serializeToJson(event);

        OutboxEvent outboxEvent = OutboxEvent.builder()
                .eventType("PAYMENT_EVENT")
                .aggregateId(senderId.toString())
                .payload(eventJson)
                .createdAt(LocalDateTime.now())
                .processed(false)
                .build();

        outboxEventRepository.save(outboxEvent);
    }

}
