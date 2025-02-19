package com.laalka.expensesservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "processed_transactions",
        uniqueConstraints = @UniqueConstraint(columnNames = "transaction_id"))
public class ProcessedTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "processed_at", nullable = false)
    private LocalDateTime processedAt;
}

