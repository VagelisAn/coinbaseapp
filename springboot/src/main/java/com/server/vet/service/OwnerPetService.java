package com.server.vet.service;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.entity.Owner;
import com.server.vet.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface OwnerPetService {
    OwnerDTO saveOrUpdateOwner(OwnerDTO ownerDTO, Long id);

    OwnerDTO updateOwnerWithPets(Owner existingOwner, OwnerDTO newOwnerDTO);

    OwnerDTO createNewOwnerWithPets(OwnerDTO ownerDTO);

    List<Pet> savePetsWithOwner(Owner owner, List<Pet> pets);

    PetDTO assignPetToOwner(Long petId, Long ownerId);

    PetDTO updatePet(PetDTO newPetDTO, Optional<Pet> oldPet);

    PetDTO addPetUpdateOwner(PetDTO petDTO, Long OwnerId);

    OwnerDTO replaceOwnerPets(PetDTO newPetDTO, Optional<Owner> oldOwner);

    OwnerDTO createOwnerWithPets(OwnerDTO ownerDTO);
}
