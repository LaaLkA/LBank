package com.laalka.authservice.controllers;

import com.laalka.authservice.dto.WebUser;
import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.services.AuthService;
import com.laalka.authservice.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
    }

    /**
     * Метод должен получать token, парсить его и возвращать имя пользователя
     * @param currentUser
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<WebUser> getUserName(@AuthenticationPrincipal String currentUser) {
        AuthUser user = authService.getUser(currentUser);

        WebUser webUser = new WebUser();
        webUser.setUsername(user.getUsername());
        webUser.setRole(user.getRole());
        webUser.setTimeCreated(user.getTimeCreated());
        return ResponseEntity.ok(webUser);
    }
}
