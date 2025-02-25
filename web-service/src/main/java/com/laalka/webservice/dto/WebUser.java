package com.laalka.webservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class WebUser {
    private String username;
    private String role;
    private LocalDateTime timeCreated;
}

