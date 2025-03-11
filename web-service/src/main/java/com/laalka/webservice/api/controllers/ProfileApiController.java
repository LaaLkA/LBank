package com.laalka.webservice.api.controllers;

import com.laalka.webservice.api.dto.ProfileResponse;
import com.laalka.webservice.api.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileApiController {

    private final ProfileService profileService;

    public ProfileApiController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ProfileResponse> profile(@PathVariable String userName,
                                                   @CookieValue(name = "JWT_TOKEN", required = false) String token) {
        System.out.println("Profile response with USERNAME: " + userName);
        ProfileResponse profile = profileService.getProfile(userName, token);
        if (profile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(profile);
    }
}
