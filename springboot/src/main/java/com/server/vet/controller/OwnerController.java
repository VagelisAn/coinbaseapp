package com.server.vet.controller;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.dto.PetDTO;
import com.server.vet.dto.registration.steps.OwnerRegistrationDTO;
import com.server.vet.service.impl.OwnerAsUserServiceImpl;
import com.server.vet.service.impl.OwnerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerServiceImpl ownerServiceImpl;

    private final OwnerAsUserServiceImpl ownerAsUserServiceImpl;


    @GetMapping("/all")
    public List<OwnerDTO> all() throws IOException {
        return ownerServiceImpl.all();
    }

    @PostMapping()
    public OwnerDTO newOwner(@RequestBody OwnerDTO newOwner) {
        return ownerServiceImpl.newOwners(newOwner);
    }


    @GetMapping("/{id}")
    public OwnerDTO one(@PathVariable Long id) {
        return ownerServiceImpl.one(id);
    }

    @PutMapping("/{id}")
    public OwnerDTO replaceOwners(
            @RequestBody OwnerDTO newOwner,
            @PathVariable Long id
    ) {
        return ownerServiceImpl.replaceOwner(newOwner,id);
    }

    @PutMapping("/pet/{id}")
    public OwnerDTO replaceOwnerPets(
            @RequestBody PetDTO newPet,
            @PathVariable Long id
    ) {
        return ownerServiceImpl.replaceOwnerPets(newPet,id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerServiceImpl.deleteOwner(id);
    }

    @GetMapping("/pages")
    public Page<OwnerDTO> getProductList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return ownerServiceImpl.findAll(paging);
    }

    @PostMapping("/registrations")
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO ownerDTO) {
        return ResponseEntity.ok(ownerServiceImpl.createOwnerWithPets(ownerDTO));
    }

    @GetMapping("/user")
    public OwnerDTO getOwnerUser() {
        return ownerAsUserServiceImpl.getOwnerAsUser();
    }

    @GetMapping("/keycloak/{id}")
    public OwnerDTO oneByKeycloakId(@PathVariable String id) {
        return ownerServiceImpl.oneByKeycloakId(id);
    }

}
