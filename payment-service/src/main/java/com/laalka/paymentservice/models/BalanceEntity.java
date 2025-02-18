package com.laalka.paymentservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="balances")
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hash_user")
    private String hashUser;

    @Column(name="balance")
    private Double balance;

}
