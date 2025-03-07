package com.server.vet.exception.keycloak;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class KeycloakUserException extends RuntimeException {
    public KeycloakUserException(String message) {
        super(message);
    }
}
