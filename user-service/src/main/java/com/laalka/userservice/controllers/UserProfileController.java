package com.laalka.userservice.controllers;

import com.laalka.userservice.dto.RegistrationRequest;
import com.laalka.userservice.models.UserProfile;
import com.laalka.userservice.services.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(
            @RequestBody RegistrationRequest req) {
        UserProfile created = profileService.createProfile(req.getUserName());
        return ResponseEntity.ok("Profile created for user=" + created.getUsername());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getProfile(@PathVariable String userName) {
        UserProfile UserProfile = profileService.getProfile(userName);
        return ResponseEntity.ok(UserProfile);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateProfile(
            @PathVariable String username,
            @RequestParam(required=false) String email,
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName,
            @RequestParam(required=false) String phone
    ) {
        UserProfile updated = profileService.updateProfile(username, email, firstName, lastName, phone);
        return ResponseEntity.ok("Profile updated for user=" + updated.getUsername());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteProfile(@PathVariable String username) {
        profileService.deleteProfile(username);
        return ResponseEntity.ok("Profile deleted for user=" + username);
    }
}

