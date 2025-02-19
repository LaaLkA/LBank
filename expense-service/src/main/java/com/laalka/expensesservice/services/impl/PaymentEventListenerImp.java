package com.laalka.expensesservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laalka.expensesservice.models.ProcessedTransactionEntity;
import com.laalka.expensesservice.repository.ExpensesRepository;
import com.laalka.expensesservice.repository.ProcessedTransactionRepository;
import com.laalka.expensesservice.services.ExpenseService;
import com.laalka.expensesservice.services.PaymentEventListener;
import com.laalka.events.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDateTime;

public class PaymentEventListenerImp implements PaymentEventListener {
    private final ProcessedTransactionRepository processedTxRepo;
    private final ExpenseService expenseService;
    private final ObjectMapper objectMapper;

    public PaymentEventListenerImp(ProcessedTransactionRepository processedTxRepo,
                                   ExpenseService expenseService,
                                   ObjectMapper objectMapper) {
        this.processedTxRepo = processedTxRepo;
        this.expenseService = expenseService;
        this.objectMapper = objectMapper;
    }

    @Override
    @KafkaListener(
            topics = "paymentCompletedTopic",
            groupId = "expenses-group"
    )
    public void onMessage(String message) {
        try {
            PaymentEvent event = objectMapper.readValue(message, PaymentEvent.class);

            if (alreadyProcessed(event.getTransactionId())) {
                return;
            }

            expenseService.createExpense(
                    event.getSenderId(),
                    event.getReceiverId(),
                    event.getAmount(),
                    "transfer"
            );

            markProcessed(event.getTransactionId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean alreadyProcessed(String transactionId) {
        return processedTxRepo.existsByTransactionId(transactionId);
    }

    private void markProcessed(String transactionId) {
        ProcessedTransactionEntity entity = new ProcessedTransactionEntity();
        entity.setTransactionId(transactionId);
        entity.setProcessedAt(LocalDateTime.now());
        processedTxRepo.save(entity);
    }
}
