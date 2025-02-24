package com.laalka.authservice.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сервис, который делает HashCode слепок пользователя
 */
@Component
public class HashService {

    public String userHash(String userName, LocalDateTime userCreated) {
        String userData = userName + userCreated;
        return sha256(userData);
    }

    public String userTransactionHash(UUID userId, String userName, LocalDateTime userCreated) {
        String userData = userId.toString() + userName + userCreated + LocalDateTime.now();
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
