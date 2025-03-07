package com.server.vet.service.impl;

import com.server.vet.dto.PetDTO;
import com.server.vet.entity.*;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.mapper.*;
import com.server.vet.repository.*;
import com.server.vet.repository.pagination.OwnerPaginationRepository;
import com.server.vet.service.OwnerPetService;
import com.server.vet.service.PetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {


    private final PetRepository petRepository;

    private final OwnerPetService ownerPetService;

    private final PetMapper petMapper;

    private final OwnerMapper ownerMapper;

    @Override
    public List<PetDTO> all() {
        return petMapper.withOutChildDTOs(petRepository.findAll());
    }
    @Override
    public List<PetDTO> noOwner() {
        return petRepository.findAll().stream()
                .filter(pet -> pet.getOwner() != null)
                .map(petMapper::mapToPetDTO)
                .toList();
    }
    @Override
    public PetDTO newPet(PetDTO newPet) {
        Pet pet = petMapper.mapToPet(newPet);

        Optional.ofNullable(newPet.getOwnerDTO())
                .map(ownerMapper::mapToOwner)
                .ifPresent(pet::setOwner);

        return petMapper.mapToPetDTO(petRepository.save(pet));
    }
    @Override
    public PetDTO one(Long id) {
        return petMapper.mapToPetDTO(getPetById(id));
    }
    @Override
    public List<PetDTO> findByOwnerId(Long id){
        return(petMapper.mapToPet(petRepository.findByOwnerId(id)));
    }

    @Override
    public PetDTO updatePet(PetDTO newPetDTO, Long id) {
        newPetDTO.setId(id);
        return ownerPetService.updatePet(newPetDTO,petRepository.findById(id));
    }
    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public List<PetDTO> onlyPets() {
        return petMapper.withOutChildDTOs(petRepository.findAll());
    }

    @Override
    public List<String> getAllBreeds() {
        return petRepository.findAllBreeds().stream()
                .filter(str -> str != null && !str.trim().isEmpty()) // Filter out null and empty strings
                .distinct() // Remove duplicates
                .sorted(String.CASE_INSENSITIVE_ORDER) // Sort in a case-insensitive order
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllSpecies() {
        return petRepository.findAllSpecies().stream()
                .filter(str -> str != null && !str.trim().isEmpty()) // Filter out null and empty strings
                .distinct() // Remove duplicates
                .sorted(String.CASE_INSENSITIVE_ORDER) // Sort in a case-insensitive order
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllColors() {
        return petRepository.findAllColors().stream()
                .filter(str -> str != null && !str.trim().isEmpty()) // Filter out null and empty strings
                .distinct() // Remove duplicates
                .sorted(String.CASE_INSENSITIVE_ORDER) // Sort in a case-insensitive order
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO replaceOwner(Long petId, Long ownerId) {
        return ownerPetService.assignPetToOwner(petId, ownerId);
    }

    @Override
    public PetDTO addPetUpdateOwner(PetDTO petDTO, Long ownerId) {
        return ownerPetService.addPetUpdateOwner(petDTO, ownerId);
    }
    @Override
    public Pet getPetById(Long petId) {
        return  petRepository.findById(petId)
        .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + petId));

    }

}
