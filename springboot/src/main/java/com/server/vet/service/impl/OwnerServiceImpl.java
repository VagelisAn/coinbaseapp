package com.server.vet.service.impl;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.dto.UserCreationResponseDTO;
import com.server.vet.entity.Owner;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.exception.keycloak.KeycloakStatusMessageMapper;
import com.server.vet.exception.keycloak.KeycloakUserException;
import com.server.vet.mapper.OwnerMapper;
import com.server.vet.repository.OwnerRepository;
import com.server.vet.repository.pagination.OwnerPaginationRepository;
import com.server.vet.service.OwnerPetService;
import com.server.vet.service.OwnerService;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


@Service
@AllArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerPaginationRepository ownerPaginationRepository;

    private final OwnerPetService ownerPetService;

    private final KeycloakUserServiceImpl keycloakUserService;

    private final KeycloakStatusMessageMapper statusMessageMapper;

    private final OwnerMapper ownerMapper;
    @Override
    public List<OwnerDTO> all() throws IOException {
        return ownerMapper.mapToOwnerWithBasicPetDTOs(ownerRepository.findAll()
                .stream().sorted(Comparator.comparing(Owner::getLastname)).toList());
    }
    @Override
    public OwnerDTO replaceOwnerPets(PetDTO newPetDTO, Long id) {
        return ownerPetService.replaceOwnerPets(newPetDTO, ownerRepository.findById(id));
    }

    @Override
    public Page<OwnerDTO> findAll(Pageable pageable) {
        return ownerPaginationRepository.findAll(pageable)
                .map(ownerMapper::mapToOwnerDTO);
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

    @Override
    public OwnerDTO newOwners(OwnerDTO newOwnerDTO) {
    UserCreationResponseDTO userCreationResponseDTO = keycloakUserService.createUser(newOwnerDTO);

    if (userCreationResponseDTO.getStatusCode() == Response.Status.CREATED.getStatusCode()) {
        newOwnerDTO.setKeycloakId(userCreationResponseDTO.getUserId());
        return ownerPetService.createNewOwnerWithPets(newOwnerDTO);
    }
    String errorMessage = statusMessageMapper.getMessageByStatus(userCreationResponseDTO.getStatusCode());
    throw new KeycloakUserException("Failed to create user: " + errorMessage);
    }
    @Override
    public OwnerDTO one(Long id) {
       return ownerRepository.findById(id)
                .map(ownerMapper::mapToOwnerDTO)
                .orElse(null);
    }
    @Override
    public OwnerDTO replaceOwner(OwnerDTO newOwnerDTO, Long id) {
        return ownerPetService.saveOrUpdateOwner(newOwnerDTO, id);
    }
    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
    @Override
    public OwnerDTO createOwnerWithPets(OwnerDTO ownerDTO) {
        return ownerPetService.createNewOwnerWithPets(ownerDTO);
    }

    public Owner getOwnerByPetId(Long id) {
       return ownerRepository.findByPetId(id)
        .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + id));
    }

    @Override
    public OwnerDTO oneByKeycloakId(String keycloakId) {
        return ownerRepository.findByKeycloakId(keycloakId).map(ownerMapper::mapToOwnerDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + keycloakId));
    }

}
