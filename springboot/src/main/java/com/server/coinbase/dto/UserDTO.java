package com.server.coinbase.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {
    private String email;
    private String role;
    private Long id;
    private String firstname;
    private String lastname;
    private String city;
    private String postcode;
    private String address;
    private String mobile;
    private String phone;
    private String contact;
    private String keycloakId;

    // Getters & Setters
}
