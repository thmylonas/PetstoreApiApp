package com.thomasmylonas.petstore_api_app.services;

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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = MockitoExtension.class)
public class UserServiceImplFailureTest {

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
    @DisplayName(value = "Given: Username, When: findUserByUsername is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_Username_When_FindUserByUsernameIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final String USERNAME = "username";

        when(mockUserRepository.findByUsername(USERNAME)).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> userService.findUserByUsername(USERNAME));
    }

    @Test
    @DisplayName(value = "When: saveUser is called, Then: IllegalArgumentException is thrown")
    public void test_When_SaveUserIsCalled_Then_IllegalArgumentExceptionIsThrown() {

        // Given / Arrange

        when(mockUserMapper.toUser(any())).thenReturn(null);
        when(mockUserRepository.save(null)).thenThrow(IllegalArgumentException.class);

        // When / Act -  Then / Assert

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(any()));
    }

    @Test
    @DisplayName(value = "Given: UsernameToUpdate, When: updateUser is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_UsernameToUpdate_When_UpdateUserIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final String USERNAME_TO_UPDATE = "username";

        when(mockUserRepository.findByUsername(USERNAME_TO_UPDATE)).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockUserRepository).save(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        verify(mockUserRepository, never()).save(any());
        assertThrows(RequestedResourceNotFoundException.class, () -> userService.updateUser(USERNAME_TO_UPDATE, any()));
        //assertThrows(IllegalArgumentException.class, () -> userService.updateUser(USERNAME_TO_UPDATE, any())); // Will never happen, because of the "RequestedResourceNotFoundException"
    }

    @Test
    @DisplayName(value = "Given: username, When: deleteUserByUsername is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_Username_When_DeleteUserByUsernameIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final String USERNAME = "username";

        when(mockUserRepository.findByUsername(USERNAME)).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockUserRepository).delete(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        verify(mockUserRepository, never()).delete(any());
        assertThrows(RequestedResourceNotFoundException.class, () -> userService.deleteUserByUsername(USERNAME));
        //assertThrows(IllegalArgumentException.class, () -> userService.deleteUserByUsername(USERNAME)); // Will never happen, because of the "RequestedResourceNotFoundException"
    }
}
