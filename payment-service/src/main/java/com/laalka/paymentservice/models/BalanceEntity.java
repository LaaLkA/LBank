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

    @Column(name="username")
    private String userName;

    @Column(name="balance")
    private Double balance;

}
