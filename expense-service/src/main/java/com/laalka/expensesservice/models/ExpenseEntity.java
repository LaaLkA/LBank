package com.laalka.expensesservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="expenses")
public class ExpenseEntity {
    @Id
    @GeneratedValue()
    private UUID id;

    @Column(name="user_id")
    private String sender;

    @Column(name="receiver_id")
    private String receiver;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    private String category;

//    @Column(name = "transaction_id", unique = true)
//    private String transactionId;


}
