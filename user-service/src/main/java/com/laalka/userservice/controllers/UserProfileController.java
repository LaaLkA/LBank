package com.laalka.userservice.controllers;

import com.laalka.userservice.dto.ProfileResponse;
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
        ProfileResponse UserProfile = profileService.getProfile(userName);
        return ResponseEntity.ok(UserProfile);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<ProfileResponse> updateProfile(
            @PathVariable String userName,
            @RequestBody ProfileResponse profileData
    ) {
        UserProfile updated = profileService.updateProfile(
                userName,
                profileData.getEmail(),
                profileData.getFirstName(),
                profileData.getLastName(),
                profileData.getPhone()
        );

        ProfileResponse response = new ProfileResponse();
        response.setUserName(updated.getUsername());
        response.setFirstName(updated.getFirstName());
        response.setLastName(updated.getLastName());
        response.setEmail(updated.getEmail());
        response.setPhone(updated.getPhone());

        return ResponseEntity.ok(response);
    }

}

