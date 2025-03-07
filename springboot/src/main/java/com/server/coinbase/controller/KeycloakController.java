package com.server.coinbase.controller;

import com.server.coinbase.dto.UserDTO;
import com.server.coinbase.service.internal.impl.UserServiceImpl;
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

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/spring-boot-event-listener")
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO newUser) {
        UserDTO userDTO = userServiceImpl.createUser(newUser);
        HttpHeaders headers = new HttpHeaders();
        log.info("Location |http://localhost:4200/owners/"+userDTO.getId()+"/edit");

        // Επιστρέφεις και το Body (OwnerDTO)
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(headers)
                .body(userDTO);
    }

}
