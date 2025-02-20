package com.laalka.authservice.controllers;

import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.services.AuthService;
import com.laalka.authservice.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam String username,
            @RequestParam String password
    ) {
        AuthUser user = authService.register(username, password, "ROLE_USER");
        return ResponseEntity.ok("User " + user.getUsername() + " registered with ID=" + user.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        AuthUser user = authService.getUser(username);
        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        AuthUser user = authService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{username}/update")
    public ResponseEntity<?> updateUser(
            @PathVariable String username,
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String role
    ) {
        AuthUser updated = authService.updateUser(username, newUsername, role);
        return ResponseEntity.ok("Updated user " + updated.getUsername());
    }

    @PostMapping("/user/{username}/change-password")
    public ResponseEntity<?> changePassword(
            @PathVariable String username,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        authService.changePassword(username, oldPassword, newPassword);
        return ResponseEntity.ok("Password changed for user " + username);
    }

    @PostMapping("/user/{username}/reset-password")
    public ResponseEntity<?> resetPassword(
            @PathVariable String username,
            @RequestParam String newPassword
    ) {
        authService.resetPassword(username, newPassword);
        return ResponseEntity.ok("Password reset for user " + username);
    }
}
