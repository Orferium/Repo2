package com.itm.space.backendresources.service;

import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.exception.BackendResourcesException;
import com.itm.space.backendresources.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.WebApplicationException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest {

    @Mock
    private Keycloak keycloakClient;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUserThrowsException() {
        UserRequest userRequest = new UserRequest("testuser", "test@example.com", "password", "Test", "User");
        doThrow(new WebApplicationException("User creation failed", 400))
                .when(keycloakClient).realm(anyString()).users().create(any());

        assertThrows(BackendResourcesException.class, () -> userService.createUser(userRequest));
    }

    @Test
    public void testGetUserById() {
        UUID userId = UUID.randomUUID();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(userId.toString());
        userRepresentation.setUsername("testuser");

        when(keycloakClient.realm(anyString()).users().get(userId.toString()).toRepresentation()).thenReturn(userRepresentation);

        UserResponse userResponse = userService.getUserById(userId);
        assertEquals("testuser", userResponse.getFirstName());
    }
}