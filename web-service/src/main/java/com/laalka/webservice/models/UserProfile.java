package com.laalka.webservice.models;

import lombok.Data;

@Data
public class UserProfile {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}
