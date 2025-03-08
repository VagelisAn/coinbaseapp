package com.server.coinbase.service.internal.impl;


import com.server.coinbase.dto.UserCreationResponseDTO;
import com.server.coinbase.dto.UserDTO;
import com.server.coinbase.exception.keycloak.KeycloakStatusMessageMapper;
import com.server.coinbase.exception.keycloak.KeycloakUserException;
import com.server.coinbase.service.internal.KeycloakUserService;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private final Keycloak keycloak;

    private final KeycloakStatusMessageMapper statusMessageMapper;

    @Value("${keycloak.k.realm}")
    private String realmMaster;

    @Value("${keycloak.resource}")
    private String clientIdResource;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.role.user}")
    private String userRole;



    public KeycloakUserServiceImpl(Keycloak keycloak, KeycloakStatusMessageMapper statusMessageMapper) {
        this.keycloak = keycloak;
        this.statusMessageMapper = statusMessageMapper;
    }

    @Override
    public UserCreationResponseDTO createUser(UserDTO newUserDto) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(newUserDto.getEmail().split("@")[0]);
        user.setEmail(newUserDto.getEmail());
        user.setFirstName(newUserDto.getFirstname());
        user.setLastName(newUserDto.getLastname());
        user.setEnabled(true);
        user.setRealmRoles(Collections.singletonList("owner"));

        // Δημιουργία Credentials
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newUserDto.getMobile());
        credential.setTemporary(false);
        user.setCredentials(Collections.singletonList(credential));

        int status = 0;
        String userId = "";
        try (Response result = keycloak.realm(realm).users().create(user)) {
            status = result.getStatus();
            userId = result.getLocation().getPath().replaceAll(".*/", "");

        } catch (Exception e) {
            String errorMessage = statusMessageMapper.getMessageByStatus(status);
            throw new KeycloakUserException(errorMessage);
        }
        return new UserCreationResponseDTO(userId, status);
    }
}
