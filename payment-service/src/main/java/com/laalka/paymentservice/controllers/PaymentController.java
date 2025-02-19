package com.laalka.paymentservice.controllers;

import com.laalka.paymentservice.dto.TransferRequest;
import com.laalka.paymentservice.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest request) {
        paymentService.transaction(
                request.getSenderId(),
                request.getSenderName(),
                request.getSenderCreated(),
                request.getAmount(),
                request.getReceiverId(),
                request.getReceiverName(),
                request.getReceiverCreated()
        );
        return ResponseEntity.ok("Transfer successful");
    }




}
