package com.laalka.authservice.services;

import com.laalka.authservice.api.service.BalanceService;
import com.laalka.authservice.api.service.UserProfileService;
import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.repositories.AuthUserRepository;
import com.laalka.authservice.utils.HashService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileService userProfileService;
    private final HashService hashService;
    private final BalanceService balanceService;

    public AuthService(AuthUserRepository userRepository,
                       PasswordEncoder passwordEncoder, UserProfileService userProfileService, HashService hashService, BalanceService balanceService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userProfileService = userProfileService;
        this.hashService = hashService;
        this.balanceService = balanceService;
    }

    public AuthUser register(String username, String rawPassword, String role) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already taken");
        }
        AuthUser user = new AuthUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setTimeCreated(LocalDateTime.now());
        user.setUserHash(hashService.userHash(username, user.getTimeCreated()));

        userProfileService.createProfile(user.getUserHash(), user.getUsername());
        balanceService.createBalance(user.getUserHash());
        return userRepository.save(user);
    }

    public AuthUser getUser(String username) {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public AuthUser updateUser(String oldUsername, String newUsername, String role) {
        AuthUser user = userRepository.findByUsername(oldUsername);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (newUsername != null && !newUsername.isBlank()) {
            user.setUsername(newUsername);
        }
        if (role != null && !role.isBlank()) {
            user.setRole(role);
        }
        return userRepository.save(user);
    }

    public void changePassword(String username, String oldRawPassword, String newRawPassword) {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(oldRawPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newRawPassword));
        userRepository.save(user);
    }

    public void resetPassword(String username, String newRawPassword) {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setPassword(passwordEncoder.encode(newRawPassword));
        userRepository.save(user);
    }

}
