package com.server.vet.exception.keycloak;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KeycloakStatusMessageMapper {

    private static final Map<Integer, String> statusMessages = Map.of(
            400, "Invalid request",
            401, "Unauthorized",
            403, "Forbidden",
            404, "Resource not found",
            409, "User already exists",
            500, "Internal server error"
    );

    public String getMessageByStatus(int status) {
        return statusMessages.getOrDefault(status, "Unknown error occurred");
    }
}