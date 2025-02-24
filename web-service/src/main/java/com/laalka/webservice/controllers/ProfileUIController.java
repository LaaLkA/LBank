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
        UserProfile profile = profileService.getProfile(token);
        model.addAttribute("profile", profile);
        return "profile/view"; // Шаблон для отображения профиля (resources/templates/profile/view.html)
    }

    @GetMapping("/profile/edit")
    public String editProfile(@CookieValue(name = "JWT_TOKEN", required = false) String token, Model model) {
        if (token == null) {
            return "redirect:/login";
        }
        UserProfile profile = profileService.getProfile(token);
        model.addAttribute("profile", profile);
        return "profile/edit"; // Страница редактирования профиля
    }

    @PostMapping("/profile/update")
    public String updateProfile(@CookieValue(name = "JWT_TOKEN", required = false) String token,
                                @RequestParam String email,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String phone,
                                Model model) {
        if (token == null) {
            return "redirect:/login";
        }
        try {
            profileService.updateProfile(token, email, firstName, lastName, phone);
            return "redirect:/profile";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка обновления: " + e.getMessage());
            return "profile/edit";
        }
    }
}
