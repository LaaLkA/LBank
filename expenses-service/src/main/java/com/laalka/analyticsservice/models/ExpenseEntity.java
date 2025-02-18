package com.laalka.analyticsservice.models;

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

    @Column(name="receiver_name")
    private String receiverName;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    private String category;

    public ExpenseEntity() {}
    public ExpenseEntity(Long userId, String receiverName, Double amount) {
        this.userId = userId;
        this.receiverName = receiverName;
        this.amount = amount;
        this.category = "No defined";
    }
    public ExpenseEntity(Long userId, String receiverName, Double amount, String category) {
        this.userId = userId;
        this.receiverName = receiverName;
        this.amount = amount;
        this.category = category;
    }

}
