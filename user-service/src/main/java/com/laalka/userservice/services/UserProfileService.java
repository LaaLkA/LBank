package com.laalka.userservice.services;

import com.laalka.userservice.models.UserProfile;
import com.laalka.userservice.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile createProfile(String username, String email, String firstName, String lastName, String phone) {
        if (repository.findByUsername(username) != null) {
            throw new RuntimeException("Profile already exists for username=" + username);
        }
        UserProfile profile = new UserProfile();
        profile.setUsername(username);
        profile.setEmail(email);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhone(phone);
        return repository.save(profile);
    }

    public UserProfile getProfile(String username) {
        UserProfile profile = repository.findByUsername(username);
        if (profile == null) {
            throw new RuntimeException("Profile not found for user=" + username);
        }
        return profile;
    }

    public UserProfile updateProfile(String username, String email, String firstName, String lastName, String phone) {
        UserProfile profile = getProfile(username);
        if (email != null) profile.setEmail(email);
        if (firstName != null) profile.setFirstName(firstName);
        if (lastName != null) profile.setLastName(lastName);
        if (phone != null) profile.setPhone(phone);
        return repository.save(profile);
    }

    public void deleteProfile(String username) {
        UserProfile profile = getProfile(username);
        repository.delete(profile);
    }
}

