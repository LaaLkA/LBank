package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.TransferRequest;
import com.laalka.webservice.api.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferApiController {
    private final TransferService transferService;

    public TransferApiController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> doTransfer(@RequestBody TransferRequest request) {

        ResponseEntity<String> response = transferService.doTransfer(
                request.getSender(),
                request.getReceiver(),
                request.getAmount()
        );
        return response;
    }

}
