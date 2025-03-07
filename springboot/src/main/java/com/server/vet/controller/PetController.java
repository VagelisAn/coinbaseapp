package com.server.vet.controller;



import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.service.impl.PetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/pet")
public class PetController {

    private final PetServiceImpl petServiceImpl;

    @GetMapping("/all")
    List<PetDTO> all(){
        return petServiceImpl.all();
    }

    @GetMapping("/noowner")
    List<PetDTO> noOwner(){
        return petServiceImpl.noOwner();
    }

    @GetMapping("/only-pets")
    List<PetDTO> onlyPets(){
        return petServiceImpl.onlyPets();
    }

    @PostMapping()
    PetDTO newPet(@RequestBody PetDTO newPet) {
        return petServiceImpl.newPet(newPet);
    }

    @GetMapping("/{id}")
    PetDTO one(@PathVariable Long id) {
        return petServiceImpl.one(id);
    }

    @GetMapping("/owner/{id}")
    List<PetDTO> byOWnerId(@PathVariable Long id) {
        return petServiceImpl.findByOwnerId(id);
    }

    @PutMapping("/{id}")
    PetDTO replacePet(@RequestBody PetDTO newPet, @PathVariable Long id) {
        return petServiceImpl.updatePet(newPet,id);
    }

    @DeleteMapping("/{id}")
    void deletePet(@PathVariable Long id) {
        petServiceImpl.deletePet(id);
    }

    @GetMapping("/breeds")
    List<String> allBreeds(){
        return petServiceImpl.getAllBreeds();
    }

    @GetMapping("/species")
    List<String> allSpecies(){
        return petServiceImpl.getAllSpecies();
    }

    @GetMapping("/colors")
    List<String> allColors(){
        return petServiceImpl.getAllColors();
    }

    @GetMapping("/{petId}/{ownerId}")
    PetDTO replaceOwner(@PathVariable Long petId, @PathVariable Long ownerId) {
        return petServiceImpl.replaceOwner(petId,ownerId);
    }
    @PutMapping("/{petId}/owner/{ownerId}")
    PetDTO replaceOwnerNew(@PathVariable Long petId, @PathVariable Long ownerId) {
        return petServiceImpl.replaceOwner(petId,ownerId);
    }

    @PutMapping("/owner/{id}")
    public PetDTO addPetUpdateOwner(
            @RequestBody PetDTO newPet,
            @PathVariable Long id
    ) {
        return petServiceImpl.addPetUpdateOwner(newPet,id);
    }
}
