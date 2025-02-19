package com.laalka.expensesservice.services;

public interface PaymentEventListener {
    void onMessage(String message);
}
