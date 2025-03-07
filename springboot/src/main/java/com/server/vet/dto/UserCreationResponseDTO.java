package com.server.vet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserCreationResponseDTO {
    private String userId;
    private int statusCode;
}
