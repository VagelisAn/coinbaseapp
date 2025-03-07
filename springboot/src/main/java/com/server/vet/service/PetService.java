package com.server.vet.service;


import com.server.vet.dto.PetDTO;
import com.server.vet.entity.Pet;

import java.io.IOException;
import java.util.List;

public interface PetService {
    List<PetDTO> all() throws IOException;

    List<PetDTO> noOwner() throws IOException;

    List<PetDTO> findByOwnerId(Long id);

    PetDTO newPet(PetDTO newPet);

    PetDTO one(Long id);

    PetDTO updatePet(PetDTO newPet, Long id);

    void deletePet(Long id);

    List<PetDTO> onlyPets();

    List<String> getAllBreeds();

    List<String> getAllSpecies();

    List<String> getAllColors();

    PetDTO replaceOwner(Long petId, Long ownerId);

    PetDTO addPetUpdateOwner(PetDTO petDTO, Long OwnerId);

    Pet getPetById(Long petId);
}
