package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.entities.User;
import com.thomasmylonas.petstore_api_app.enums.UserStatus;
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

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplSuccessTest {

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
    @DisplayName(value = "Given: Username, When: FindUserByUsername is called, Then: UserByUsername is returned")
    public void test_Given_Username_When_FindUserByUsernameIsCalled_Then_UserByUsernameIsReturned() {

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
        final UserResponseDto USER_RESPONSE_DTO = UserResponseDto.builder()
                .id(1L)
                .username(USER.getUsername())
                .firstName(USER.getFirstName())
                .lastName(USER.getLastName())
                .email(USER.getEmail())
                .password(USER.getPassword())
                .phone(USER.getPhone())
                .userStatus(USER.getUserStatus().getValue())
                .build();
        when(mockUserRepository.findByUsername(USER.getUsername())).thenReturn(Optional.of(USER));
        when(mockUserMapper.fromUser(USER)).thenReturn(USER_RESPONSE_DTO);

        // When / Act

        UserResponseDto userByUsername = userService.findUserByUsername(USER.getUsername());

        // Then / Assert

        assertEquals(USER_RESPONSE_DTO, userByUsername);
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
