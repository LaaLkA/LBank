package com.laalka.webservice.controllers;

import com.laalka.webservice.models.UserProfile;
import com.laalka.webservice.services.ProfileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileUIController {

    private final ProfileService profileService;

    public ProfileUIController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String viewProfile(@CookieValue(name = "JWT_TOKEN", required = false) String token, Model model) {
        if (token == null) {
            return "redirect:/login";
        }
        String userName = profileService.getUserName(token);
        model.addAttribute("userName", userName);

        return "profile/view";
    }

}
