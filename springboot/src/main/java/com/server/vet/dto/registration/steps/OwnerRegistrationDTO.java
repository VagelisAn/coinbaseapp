package com.server.vet.dto.registration.steps;

import lombok.Data;

@Data
public class OwnerRegistrationDTO {
    private String firstname;
    private String lastname;
    private String mobile;
    private PetDTOList petDTOList;
}

