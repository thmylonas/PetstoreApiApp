package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.entities.User;
import com.thomasmylonas.petstore_api_app.enums.UserStatus;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.UserRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplFailureTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserMapper mockUserMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testUserLogin() {
    }

    @Test
    public void testUserLogout() {
    }

    @Test
    @DisplayName(value = "When: FindUserByUsername is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_When_FindUserByUsernameIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final User USER = User.builder()
                .id(1L)
                .username("username")
                .firstName("First_Name")
                .lastName("Last_Name")
                .email("Email")
                .password("Password")
                .phone("Phone")
                .userStatus(UserStatus.USER_STATUS_1)
                .build();
        when(mockUserRepository.findByUsername(USER.getUsername())).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> userService.findUserByUsername(USER.getUsername()));
    }

    @Test
    public void testFindAllUsers() {
    }

    @Test
    public void testSaveUser() {
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    public void testDeleteUserByUsername() {
    }
}
