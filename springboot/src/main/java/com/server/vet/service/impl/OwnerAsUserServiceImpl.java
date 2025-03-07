package com.server.vet.service.impl;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.entity.Owner;
import com.server.vet.exception.ResourceNotFoundException;
import com.server.vet.mapper.OwnerMapper;
import com.server.vet.repository.OwnerRepository;
import com.server.vet.repository.pagination.OwnerPaginationRepository;
import com.server.vet.service.OwnerAsUserService;
import com.server.vet.service.OwnerPetService;
import com.server.vet.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class OwnerAsUserServiceImpl implements OwnerAsUserService {

    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;
    @Override
    public OwnerDTO getOwnerAsUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new IllegalStateException("User is not authenticated");
        }

        String firstName = jwt.getClaim("given_name");
        String lastName = jwt.getClaim("family_name");
        String email = jwt.getClaim("email");

        Optional<Owner> owner = ownerRepository.findByFirstnameAndLastnameOrEmail(firstName, lastName, email);

        return owner.map(ownerMapper::mapToOwnerDTO)
                .orElseThrow(() -> {
                    log.error("Owner not found with email: {}", email);
                    return new IllegalStateException("Couldn't find owner");
                });

    }

}
