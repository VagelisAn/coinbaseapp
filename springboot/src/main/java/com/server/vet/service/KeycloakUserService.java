package com.server.vet.service;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.UserCreationResponseDTO;

public interface KeycloakUserService {
    UserCreationResponseDTO createUser(OwnerDTO newOwnerDTO);
}
