package com.laalka.webservice.controllers;

import com.laalka.webservice.services.BalanceService;
import com.laalka.webservice.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProfileService profileService;
    private final BalanceService balanceService;

    public HomeController(ProfileService profileService, BalanceService balanceService) {
        this.profileService = profileService;
        this.balanceService = balanceService;
    }

    @GetMapping()
    public String home(@CookieValue(name = "JWT_TOKEN", required = false)String token, Model model) {
        if (token == null) {
            return "redirect:/login";
        }
        model.addAttribute("userName", profileService.getUserName(token));
        model.addAttribute("balance", balanceService.getBalance(token));

        return "home";
    }
}
