package com.laalka.webservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping()
public class PagesController {

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/home")
    public String home(
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        if (token == null) {
            return "redirect:/login";
        }
        return "home-work";
    }

    @GetMapping("/income")
    public String income(
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        if (token == null) {
            return "redirect:/login";
        }
        return "income";
    }

    @GetMapping("/profile")
    public String profile(
            @CookieValue(name = "JWT_TOKEN", required = false) String token
    ) {
        if (token == null) {
            return "redirect:/login";
        }
        return "profile";
    }

    @GetMapping("/transfer")
    public String transfer(
            @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        if (token == null) {
            return "redirect:/login";
        }
        return "transfer";
    }

    @GetMapping("/expenses")
    public String expenses(
            @CookieValue(name = "JWT_TOKEN", required = false) String token
    ) {
        if (token == null) {
            return "redirect:/login";
        }
        return "expense";
    }
}
