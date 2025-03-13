package com.laalka.userservice.services;

import com.laalka.userservice.dto.ProfileResponse;
import com.laalka.userservice.models.UserProfile;
import com.laalka.userservice.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile createProfile(String userName) {
        if (repository.findByUsername(userName) != null) {
            throw new RuntimeException("Profile already exists for username=" + userName);
        }
        UserProfile profile = new UserProfile();
        profile.setUsername(userName);
        profile.setEmail(null);
        profile.setFirstName(null);
        profile.setLastName(null);
        profile.setPhone(null);
        return repository.save(profile);
    }

    public ProfileResponse getProfile(String username) {
        UserProfile profile = repository.findByUsername(username);
        if (profile == null) {
            throw new RuntimeException("Profile not found for user=" + username);
        }
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setUserName(profile.getUsername());
        profileResponse.setFirstName(profile.getFirstName());
        profileResponse.setLastName(profile.getLastName());
        profileResponse.setEmail(profile.getEmail());
        profileResponse.setPhone(profile.getPhone());
        return profileResponse;
    }

    public UserProfile updateProfile(String userName, String email, String firstName, String lastName, String phone) {
        UserProfile profile = repository.findByUsername(userName);
        if (profile == null) {
            throw new RuntimeException("Profile not found for user=" + userName);
        }
        profile.setEmail(email);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhone(phone);

        System.out.println("Profile updated with USERNAME: " + userName);

        return repository.save(profile);
    }


}

