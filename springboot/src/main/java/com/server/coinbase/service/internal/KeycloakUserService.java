package com.server.coinbase.service.internal;


import com.server.coinbase.dto.UserCreationResponseDTO;
import com.server.coinbase.dto.UserDTO;

public interface KeycloakUserService {
    UserCreationResponseDTO createUser(UserDTO newUserDto);
}
