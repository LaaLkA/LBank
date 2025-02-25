package com.laalka.authservice.controllers;

import com.laalka.authservice.api.BalanceService;
import com.laalka.authservice.api.UserProfileService;
import com.laalka.authservice.models.AuthUser;
import com.laalka.authservice.services.AuthService;
import com.laalka.authservice.utils.JwtUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final JwtUtil jwtUtil;
    private final BalanceService balanceService;
    private final AuthService authService;

    public BalanceController(JwtUtil jwtUtil, BalanceService balanceService, UserProfileService userProfileService, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.balanceService = balanceService;
        this.authService = authService;
    }

    //    @GetMapping()
//    public Double getBalance(@CookieValue(name = "JWT_TOKEN", required = false) String token) {
//        return balanceService.getBalance(jwtUtil.extractUsername(token));
//    }
    @GetMapping()
    public Double getBalance(@AuthenticationPrincipal String currentUser) {
        return balanceService.getBalance(
                currentUser);
    }



}
