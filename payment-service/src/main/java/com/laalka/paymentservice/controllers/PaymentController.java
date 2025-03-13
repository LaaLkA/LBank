package com.laalka.paymentservice.controllers;

import com.laalka.paymentservice.dto.TransferRequest;
import com.laalka.paymentservice.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<String> transfer(
            @RequestBody TransferRequest request,
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {

        paymentService.transaction(
                request.getSender(),
                request.getReceiver(),
                request.getAmount(),
                token
        );
        return ResponseEntity.ok("Transfer successful");
    }


}
