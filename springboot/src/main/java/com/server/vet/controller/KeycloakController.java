package com.server.vet.controller;

import com.server.vet.dto.OwnerDTO;
import com.server.vet.service.impl.OwnerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/keycloak")
@Slf4j
public class KeycloakController {

    private final OwnerServiceImpl ownerServiceImpl;

    @PostMapping("/spring-boot-event-listener")
    public ResponseEntity<OwnerDTO> newOwner(@RequestBody OwnerDTO newOwner) {
       OwnerDTO ownerDTO = ownerServiceImpl.newOwners(newOwner);
        HttpHeaders headers = new HttpHeaders();
        log.info("Location |http://localhost:4200/owners/"+ownerDTO.getId()+"/edit");

        // Επιστρέφεις και το Body (OwnerDTO)
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(headers)
                .body(ownerDTO);
    }

}
