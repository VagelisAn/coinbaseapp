package com.server.vet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String role;

    // Getters & Setters
}
