package com.laalka.authservice.services;

import com.laalka.authservice.api.UserProfileService;
import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.repositories.AuthUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileService userProfileService;

    public AuthService(AuthUserRepository userRepository,
                       PasswordEncoder passwordEncoder, UserProfileService userProfileService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userProfileService = userProfileService;
    }

    public AuthUser register(String username, String rawPassword, String role) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already taken");
        }
        AuthUser user = new AuthUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        userProfileService.createProfile(username);
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
