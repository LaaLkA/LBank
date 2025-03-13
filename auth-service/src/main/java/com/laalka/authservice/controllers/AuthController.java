package com.laalka.authservice.controllers;

import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.services.AuthService;
import com.laalka.authservice.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
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
            @RequestParam String userName,
            @RequestParam String password
    ) {
        AuthUser user = authService.register(userName, password, "ROLE_USER");
        return ResponseEntity.ok("User " + user.getUsername() + " registered with ID=" + user.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String userName,
            @RequestParam String password
    ) {
        AuthUser user = authService.getUser(userName);
        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> getUser(@PathVariable String userName) {
        AuthUser user = authService.getUser(userName);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userName}/update")
    public ResponseEntity<?> updateUser(
            @PathVariable String userName,
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String role
    ) {
        AuthUser updated = authService.updateUser(userName, newUsername, role);
        return ResponseEntity.ok("Updated user " + updated.getUsername());
    }

    @PostMapping("/user/{userName}/change-password")
    public ResponseEntity<?> changePassword(
            @PathVariable String userName,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        authService.changePassword(userName, oldPassword, newPassword);
        return ResponseEntity.ok("Password changed for user " + userName);
    }

    @PostMapping("/user/{userName}/reset-password")
    public ResponseEntity<?> resetPassword(
            @PathVariable String userName,
            @RequestParam String newPassword
    ) {
        authService.resetPassword(userName, newPassword);
        return ResponseEntity.ok("Password reset for user " + userName);
    }
}
