package com.server.vet.dto.registration.steps;

import lombok.Data;

import java.util.List;

@Data
public class PetDTOList {
    private List<PetDTO> pets;
}
