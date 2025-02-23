package com.laalka.userservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users_profile")
public class UserProfile {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String username;

    private String email;
    private String firstName;
    private String lastName;
    private String phone;

}
