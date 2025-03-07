package com.server.vet.dto.registration.steps;

import lombok.Data;

@Data
public class PetDTO {
    private String name;
    private SpeciesDTO species;
    private GenderDTO gender;
    private String color;
    private BreedDTO breed;
    private String microchip;
    private String dateOfBirth;
}
