package com.laalka.userservice.controllers;

import com.laalka.userservice.models.UserEntity;
import com.laalka.userservice.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username,
                                      @RequestParam String email,
                                      @RequestParam String password) {
        UserEntity user = userService.registerUser(username, email, password);
        return ResponseEntity.ok("User registered successfully with ID: " + user.getId());
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String username,
//                                   @RequestParam String password) {
//
//        UserEntity user = userService.findByUsername(username);
//        if (user == null)
//            return ResponseEntity.badRequest().body("User not found");
//
//        boolean matches = userService.getPasswordEncoder().matches(password, user.getPassword());
//        if (!matches) {
//            return ResponseEntity.badRequest().body("Invalid password");
//        }
//
//        SecretKey secretKey = Keys.hmacShaKeyFor("mysecret".getBytes(StandardCharsets.UTF_8));
//        String token = Jwts.builder()
//                .setSubject(user.getUsername())
//                .claim("role", user.getRole())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 час
//                .signWith(secretKey, SignatureAlgorithm.HS256)
//                .compact();
//
//        return ResponseEntity.ok(token);
//    }
}
