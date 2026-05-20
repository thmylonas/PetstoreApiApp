package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.entities.User;
import com.thomasmylonas.petstore_api_app.enums.UserStatus;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.UserRepository;
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
public class UserServiceImplFailureTest {

    @Mock
    private UserRepository mockUserRepository;

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
    @DisplayName(value = "Given: UsernameToUpdate and UserRequestDto, When: updateUser is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_UsernameToUpdate_And_UserRequestDto_When_UpdateUserIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final String USERNAME_TO_UPDATE = "username";

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
        final UserRequestDto USER_REQUEST_DTO = UserRequestDto.builder()
                .username("new_username")
                .firstName("New_First_Name")
                .lastName("New_Last_Name")
                .email("new-username@mail.com")
                .password("new_password")
                .phone("222222222")
                .userStatus(UserStatus.USER_STATUS_2.getValue())
                .build();
        when(mockUserRepository.findByUsername(USER.getUsername())).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockUserRepository).save(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        verify(mockUserRepository, never()).save(USER);
        assertThrows(RequestedResourceNotFoundException.class, () -> userService.updateUser(USERNAME_TO_UPDATE, USER_REQUEST_DTO));
        //assertThrows(IllegalArgumentException.class, () -> userService.updateUser(USERNAME_TO_UPDATE, USER_REQUEST_DTO)); // Will never happen, because of the "RequestedResourceNotFoundException"
    }

    @Test
    @DisplayName(value = "Given: username, When: deleteUserByUsername is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_Username_When_DeleteUserByUsernameIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

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
        //doThrow(IllegalArgumentException.class).when(mockUserRepository).delete(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        verify(mockUserRepository, never()).delete(USER);
        assertThrows(RequestedResourceNotFoundException.class, () -> userService.deleteUserByUsername(USER.getUsername()));
        //assertThrows(IllegalArgumentException.class, () -> userService.deleteUserByUsername(USER.getUsername())); // Will never happen, because of the "RequestedResourceNotFoundException"
    }
}
