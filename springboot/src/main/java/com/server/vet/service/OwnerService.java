package com.server.vet.service;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.dto.registration.steps.OwnerRegistrationDTO;
import com.server.vet.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface OwnerService {
    List<OwnerDTO> all() throws IOException;

    OwnerDTO newOwners(OwnerDTO newOwner);

    OwnerDTO one(Long id);

    OwnerDTO replaceOwner(OwnerDTO newOwner, Long id);

    void deleteOwner(Long id);

    OwnerDTO replaceOwnerPets(PetDTO newPetDTO, Long id);

    Page<OwnerDTO> findAll(Pageable pageable);

    OwnerDTO createOwnerWithPets(OwnerDTO ownerDTO);

    Owner getOwnerByPetId(Long id);

    OwnerDTO oneByKeycloakId(String keycloakId);
}
