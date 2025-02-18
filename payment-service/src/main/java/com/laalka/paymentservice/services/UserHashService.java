package com.laalka.paymentservice.services;

import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * Сервис, который делает HashCode слепок пользователя
 */
@Service
public class UserHashService {

    public String userHash(Long userId, String userName, LocalDateTime userCreated) {
        String userData = userId.toString() + userName + userCreated;
        return sha256(userData);
    }

    private String sha256(String inputData) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(inputData.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error creating SHA-256 hash", e);
        }
    }

}
