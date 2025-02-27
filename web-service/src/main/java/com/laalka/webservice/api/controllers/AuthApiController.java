package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.AuthRequest;
import com.laalka.webservice.api.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class AuthApiController {

    private final AuthService authService;

    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> doLogin(@RequestBody AuthRequest authRequest,
                                     HttpServletResponse response) {
        try {
            String token = authService.login(
                    authRequest.getUserName(),
                    authRequest.getPassword()
            );

            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("{\"status\":\"ok\"}");
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@RequestBody AuthRequest authRequest) {
        try {
            authService.register(
                    authRequest.getUserName(),
                    authRequest.getPassword()
            );
            return ResponseEntity.ok("{\"status\":\"registered\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}

