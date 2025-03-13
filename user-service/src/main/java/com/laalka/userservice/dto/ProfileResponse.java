package com.laalka.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
