package com.laalka.expensesservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="expenses")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="receiver_id")
    private Long receiverId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    public ExpenseEntity() {}
    public ExpenseEntity(Long userId, Long receiverId, Double amount) {
        this.userId = userId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.category = "No defined";
    }
    public ExpenseEntity(Long userId, Long receiverId, Double amount, String category) {
        this.userId = userId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.category = category;
    }

}
