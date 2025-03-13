package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.TransferRequest;
import com.laalka.webservice.api.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
public class TransferApiController {
    private final TransferService transferService;

    public TransferApiController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> doTransfer(@RequestBody TransferRequest request,
                                             @CookieValue(name = "JWT_TOKEN", required = false) String token,
                                             @CookieValue(name = "USER_NAME", required = false) String sender) {


        if (sender.equals(request.getReceiver())) {
            throw new RuntimeException("Sender and receiver cannot be the same");
        }
        ResponseEntity<String> response = transferService.doTransfer(
                sender,
                request.getReceiver(),
                request.getAmount(),
                token
        );
        return response;
    }

}
