package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
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

import java.util.Optional;

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

        final Long USER_ID = 1L;
        final User USER = User.builder()
                .id(USER_ID)
                .username("username")
                .firstName("First_Name")
                .lastName("Last_Name")
                .email("username@mail.com")
                .password("password")
                .phone("111111111")
                .userStatus(UserStatus.USER_STATUS_1)
                .build();
        when(mockUserRepository.findByUsername(USER.getUsername())).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> userService.findUserByUsername(USER.getUsername()));
    }

    @Test
    @DisplayName(value = "Given: User, When: saveUser is called, Then: IllegalArgumentException is thrown")
    public void test_Given_User_When_SaveUserIsCalled_Then_IllegalArgumentExceptionIsThrown() {

        // Given / Arrange

        final UserRequestDto USER_REQUEST_DTO = UserRequestDto.builder()
                .username("username")
                .firstName("First_Name")
                .lastName("Last_Name")
                .email("username@mail.com")
                .password("password")
                .phone("111111111")
                .userStatus(UserStatus.USER_STATUS_1.getValue())
                .build();

        when(mockUserRepository.save(null)).thenThrow(IllegalArgumentException.class);

        // When / Act -  Then / Assert

        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(USER_REQUEST_DTO));
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    @DisplayName(value = "Given: OrderId, When: deleteUserByUsername is called, Then: IllegalArgumentException is thrown")
    public void test_Given_OrderId_When_DeleteUserByUsernameIsCalled_Then_IllegalArgumentExceptionIsThrown() {

        // Given / Arrange

        final Long USER_ID = 1L;
        final User USER = User.builder()
                .id(USER_ID)
                .username("username")
                .firstName("First_Name")
                .lastName("Last_Name")
                .email("username@mail.com")
                .password("password")
                .phone("111111111")
                .userStatus(UserStatus.USER_STATUS_1)
                .build();
        when(mockUserRepository.findByUsername(USER.getUsername())).thenReturn(Optional.of(USER));

        // When / Act

        userService.deleteUserByUsername(USER.getUsername());

        // Then / Assert

        verify(mockUserRepository, times(1)).delete(USER);
    }
}
