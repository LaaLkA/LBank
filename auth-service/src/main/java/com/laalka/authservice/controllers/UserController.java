package com.laalka.authservice.controllers;

import com.laalka.authservice.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private final JwtUtil jwtUtil;

    public UserController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Метод должен получать token, парсить его и возвращать имя пользователя
     * @param authHeader
     * @return
     */
    @GetMapping("/userName")
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(jwtUtil.extractUsername(token));
    }
}
