package com.laalka.webservice.controllers;

import com.laalka.webservice.services.GatewayClientService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.Cookie;


@Controller
@RequestMapping("/auth/ui")
public class AuthUIController {

    private final GatewayClientService gatewayClient;

    public AuthUIController(GatewayClientService gatewayClient) {
        this.gatewayClient = gatewayClient;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login"; // templates/auth/login.html
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model,
                          HttpServletResponse response) {
        try {
            // вызывем auth-service -> /auth/login
            String token = gatewayClient.login(username, password);

            // Сохраняем JWT, например, в cookie
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials");
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        try {
            gatewayClient.register(username, password);
            // После регистрации можем перенаправлять на login
            return "redirect:/auth/ui/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }
}

