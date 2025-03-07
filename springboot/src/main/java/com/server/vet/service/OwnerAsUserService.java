package com.server.vet.service;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface OwnerAsUserService {

    OwnerDTO getOwnerAsUser();
}
