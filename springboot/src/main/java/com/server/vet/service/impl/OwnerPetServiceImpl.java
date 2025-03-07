package com.server.vet.service.impl;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Pet;
import com.server.vet.mapper.OwnerMapper;
import com.server.vet.mapper.PetMapper;
import com.server.vet.repository.OwnerRepository;
import com.server.vet.repository.PetRepository;
import com.server.vet.service.OwnerPetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
public class OwnerPetServiceImpl implements OwnerPetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final PetMapper petMapper;
    private final OwnerMapper ownerMapper;

    @Override
    public OwnerDTO saveOrUpdateOwner(OwnerDTO ownerDTO, Long id) {
        return ownerRepository.findById(id)
                .map(existingOwner -> updateOwnerWithPets(existingOwner, ownerDTO))
                .orElseGet(() -> createNewOwnerWithPets(ownerDTO));
    }

    @Override
    public OwnerDTO updateOwnerWithPets(Owner existingOwner, OwnerDTO newOwnerDTO) {
        Owner updatedOwner = ownerMapper.mapToOwner(newOwnerDTO);
        updatedOwner.setId(existingOwner.getId());

        List<Pet> pets = updatedOwner.getPetList();
        updatedOwner.setPetList(null); // Temporarily clear pets before saving

        updatedOwner.setPetList(savePetsWithOwner(updatedOwner, pets));

        Owner savedOwner = ownerRepository.saveAndFlush(updatedOwner);
        return ownerMapper.mapToOwnerDTO(savedOwner);
    }

    @Override
    public OwnerDTO createNewOwnerWithPets(OwnerDTO ownerDTO) {
        Owner newOwner = ownerMapper.mapToOwner(ownerDTO);
        newOwner.setId(null);

        List<Pet> pets = newOwner.getPetList();
        newOwner.setPetList(null); // Temporarily clear pets before saving

        Owner savedOwner = ownerRepository.save(newOwner);

        savedOwner.setPetList(savePetsWithOwner(savedOwner, pets));

        return ownerMapper.mapToOwnerDTO(savedOwner);
    }

    @Override
    public List<Pet> savePetsWithOwner(Owner owner, List<Pet> pets) {
        if (pets == null || pets.isEmpty()) {
            return Collections.emptyList();
        }

        return pets.stream()
                .filter(pet -> pet.getId() == null) // Ensure only new pets are saved
                .map(pet -> {
                    pet.setOwner(owner);
                    return petRepository.save(pet);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO assignPetToOwner(Long petId, Long ownerId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found for ID: " + petId));

        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found for ID: " + ownerId));

        pet.setOwner(owner);
        Pet updatedPet = petRepository.save(pet);
        System.out.println("TEST "+updatedPet.toString());
        return petMapper.mapToPetDTO(updatedPet);
    }

    @Override
    public PetDTO updatePet(PetDTO newPetDTO, Optional<Pet> oldPet) {
        return oldPet.map(pet -> {
                    Pet updatedPet = petMapper.mapToPet(newPetDTO);

                    Optional<Owner> owner = ownerRepository.findByPetId(newPetDTO.getId());

                    owner.ifPresent(updatedPet::setOwner);

                    // Preserve existing associations
                    updatedPet.setWeightList(pet.getWeightList());
                    updatedPet.setExamList(pet.getExamList());
                    updatedPet.setVaccinationList(pet.getVaccinationList());
                    updatedPet.setTreatmentList(pet.getTreatmentList());
                    updatedPet.setReminderList(pet.getReminderList());
                    updatedPet.setHistoryAppointmentList(pet.getHistoryAppointmentList());

                    Pet savedPet = petRepository.save(updatedPet);
                    return petMapper.mapToPetDTO(savedPet);
                })
                .orElseGet(() -> {
                    Pet pet = petRepository.save(petMapper.mapToPet(newPetDTO));
                    return petMapper.mapToPetDTO(pet);
                });
    }

    @Override
    public PetDTO addPetUpdateOwner(PetDTO petDTO, Long ownerId) {
        return ownerRepository.findById(ownerId)
                .map(owner -> {
                    Pet newPet = petMapper.mapToPet(petDTO);

                    // Initialize new owner's pet list if null
                    List<Pet> newOwnerPets = Optional.ofNullable(owner.getPetList()).orElseGet(ArrayList::new);

                    if (newPet.getId() == null) {
                        // Case 1: New pet without an ID (not yet persisted)
                        newPet.setOwner(owner);
                        newOwnerPets.add(petRepository.save(newPet));
                    } else {
                        // Case 2: Existing pet with an ID
                        Pet existingPet = petRepository.findById(newPet.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Pet not found with ID: " + newPet.getId()));

                        // Check if the pet is already assigned to a different owner
                        if (existingPet.getOwner() != null && !existingPet.getOwner().equals(owner)) {
                            Owner previousOwner = existingPet.getOwner();

                            // Remove the pet from the previous owner's list
                            List<Pet> previousOwnerPets = Optional.ofNullable(previousOwner.getPetList())
                                    .orElseGet(ArrayList::new);
                            previousOwnerPets.remove(existingPet);

                            // Save the updated previous owner
                            previousOwner.setPetList(previousOwnerPets);
                            ownerRepository.save(previousOwner);
                        }

                        // Assign the pet to the new owner
                        existingPet.setOwner(owner);
                        newOwnerPets.add(existingPet);

                        // Persist the updated pet
                        petRepository.save(existingPet);
                    }

                    owner.setPetList(newOwnerPets);
                    ownerRepository.saveAndFlush(owner);
                    newPet.setOwner(owner);
                    return petMapper.mapToPetDTO(newPet);
                })
                .orElseThrow(() -> new EntityNotFoundException("Owner not found for ID: " + ownerId));
    }

    @Override
    public OwnerDTO replaceOwnerPets(PetDTO newPetDTO, Optional<Owner> oldOwner) {
        return oldOwner.map(owner -> {
                    Pet newPet = petMapper.mapToPet(newPetDTO);
                    newPet.setOwner(owner);

                    List<Pet> updatedPets = new ArrayList<>(owner.getPetList());
                    updatedPets.add(newPet);

                    // Save only new pets without IDs
                    updatedPets.stream()
                            .filter(pet -> pet.getId() == null)
                            .forEach(petRepository::save);

                    owner.setPetList(updatedPets);
                    Owner updatedOwner = ownerRepository.saveAndFlush(owner);

                    return ownerMapper.mapToOwnerDTO(updatedOwner);
                })
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
    }

    @Override
    @Transactional
    public OwnerDTO createOwnerWithPets(OwnerDTO ownerDTO) {

        // Map the DTO to an Owner entity
        Owner owner = ownerMapper.mapToOwner(ownerDTO);

        // Map and add pets to the owner
        List<Pet> pets = ownerDTO.getPetDTOList().stream()
                .map(petMapper::mapToPet)
                .toList();

        owner.setPetList(pets);

        // Persist the owner (and pets because of CascadeType.ALL)
        return ownerMapper.mapToOwnerDTO(ownerRepository.save(owner));
    }
}
