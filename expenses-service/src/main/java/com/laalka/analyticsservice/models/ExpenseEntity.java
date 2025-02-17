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

    @Column(name="receiver_id")
    private Long receiverId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    private String category;

}
