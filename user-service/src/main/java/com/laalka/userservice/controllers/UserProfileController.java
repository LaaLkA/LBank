package com.laalka.userservice.controllers;

import com.laalka.userservice.models.UserProfile;
import com.laalka.userservice.services.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile/create")
    public ResponseEntity<?> createProfile(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName,
            @RequestParam(required=false) String phone
    ) {
        UserProfile created = profileService.createProfile(username, email, firstName, lastName, phone);
        return ResponseEntity.ok("Profile created for user=" + created.getUsername());
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {
        UserProfile profile = profileService.getProfile(username);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile/{username}")
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

    @DeleteMapping("/profile/{username}")
    public ResponseEntity<?> deleteProfile(@PathVariable String username) {
        profileService.deleteProfile(username);
        return ResponseEntity.ok("Profile deleted for user=" + username);
    }
}

