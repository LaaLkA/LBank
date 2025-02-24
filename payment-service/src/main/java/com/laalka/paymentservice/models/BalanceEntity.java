package com.laalka.paymentservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="balances")
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="hash_user")
    private String hashUser;

    @Column(name="balance")
    private Double balance;

}
