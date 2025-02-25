package com.laalka.webservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    @GetMapping
    public String transfer() {
        return "transfer";
    }
    @PostMapping
    public ResponseEntity<> doTransfer(
            @RequestParam String receiver,
            @RequestParam Double amount) {

    }

}
