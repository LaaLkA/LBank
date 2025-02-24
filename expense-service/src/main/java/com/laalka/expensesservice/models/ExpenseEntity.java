package com.laalka.expensesservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="expenses")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="user_id")
    private String userHash;

    @Column(name="receiver_id")
    private String receiverHash;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    public ExpenseEntity() {}
    public ExpenseEntity(String userHash, String receiverHash, Double amount) {
        this.userHash = userHash;
        this.receiverHash = receiverHash;
        this.amount = amount;
        this.category = "No defined";
    }
    public ExpenseEntity(String userHash, String receiverHash, Double amount, String category) {
        this.userHash = userHash;
        this.receiverHash = receiverHash;
        this.amount = amount;
        this.category = category;
    }

}
