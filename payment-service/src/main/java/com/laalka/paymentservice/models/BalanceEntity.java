package com.laalka.paymentservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name="balances")
@Data
public class BalanceEntity {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(name="hash_user")
    private String hashUser;

    @Column(name="balance")
    private Double balance;

}
