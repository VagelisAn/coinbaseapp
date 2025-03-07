package com.server.coinbase.service.internal.impl;


import com.server.coinbase.dto.UserDTO;
import com.server.coinbase.entity.User;
import com.server.coinbase.exception.keycloak.KeycloakStatusMessageMapper;
import com.server.coinbase.mapper.UserMapper;
import com.server.coinbase.repository.UserRepository;
import com.server.coinbase.service.internal.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final KeycloakStatusMessageMapper statusMessageMapper;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userDTO.setId(existingUser.getId());
        User updatedUser = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(updatedUser));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    ////!!!!!!!!!!!!!1 Use this if you want to register also from default register page keycloack
//    @Override
//    public OwnerDTO newOwners(OwnerDTO newOwnerDTO) {
//        if (newOwnerDTO.getKeycloakId().isEmpty()) {
//
//        UserCreationResponseDTO userCreationResponseDTO = keycloakUserService.createUser(newOwnerDTO);
//
//            if (userCreationResponseDTO.getStatusCode() == Response.Status.CREATED.getStatusCode()) {
//                newOwnerDTO.setKeycloakId(userCreationResponseDTO.getUserId());
//                return ownerPetService.createNewOwnerWithPets(newOwnerDTO);
//            }
//            String errorMessage = statusMessageMapper.getMessageByStatus(userCreationResponseDTO.getStatusCode());
//            throw new KeycloakUserException("Failed to create user: " + errorMessage);
//        }
//        return ownerPetService.createNewOwnerWithPets(newOwnerDTO);
//    }

//    @Override
//    public UserDTO newOwners(UserDTO newUser) {
//    UserCreationResponseDTO userCreationResponseDTO = keycloakUserService.createUser(newOwnerDTO);
//
//    if (userCreationResponseDTO.getStatusCode() == Response.Status.CREATED.getStatusCode()) {
//        newOwnerDTO.setKeycloakId(userCreationResponseDTO.getUserId());
//        return ownerPetService.createNewOwnerWithPets(newOwnerDTO);
//    }
//    String errorMessage = statusMessageMapper.getMessageByStatus(userCreationResponseDTO.getStatusCode());
//    throw new KeycloakUserException("Failed to create user: " + errorMessage);
//    }

}
