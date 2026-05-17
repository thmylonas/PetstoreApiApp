package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.entities.User;
import com.thomasmylonas.petstore_api_app.enums.UserStatus;
import com.thomasmylonas.petstore_api_app.helpers.HelperClass;
import com.thomasmylonas.petstore_api_app.helpers.TestDataProvider;
import com.thomasmylonas.petstore_api_app.repositories.UserRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
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
    @DisplayName(value = "Given: Username, When: findUserByUsername is called, Then: UserByUsername is returned")
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
    @DisplayName(value = "Given: Users, When: findAllUsers is called, Then: allUsers are returned")
    public void test_Given_Users_When_FindAllUsersIsCalled_Then_AllUsersAreReturned() {

        // Given / Arrange

        List<UserResponseDto> USER_RESPONSE_DTOS = TestDataProvider.USER_REQUEST_DTOS.stream()
                .map(userRequestDto -> UserResponseDto.builder()
                        .id(HelperClass.RANDOM_ORDER_IDS.get(HelperClass.RANDOM.nextInt(HelperClass.RANDOM_ORDER_IDS.size())))
                        .username(userRequestDto.username())
                        .firstName(userRequestDto.firstName())
                        .lastName(userRequestDto.lastName())
                        .email(userRequestDto.email())
                        .password(userRequestDto.password())
                        .phone(userRequestDto.phone())
                        .userStatus(userRequestDto.userStatus())
                        .build()
                ).toList();
        List<User> USERS = TestDataProvider.USER_REQUEST_DTOS.stream()
                .map(userRequestDto -> User.builder()
                        .id(HelperClass.RANDOM_ORDER_IDS.get(HelperClass.RANDOM.nextInt(HelperClass.RANDOM_ORDER_IDS.size())))
                        .username(userRequestDto.username())
                        .firstName(userRequestDto.firstName())
                        .lastName(userRequestDto.lastName())
                        .email(userRequestDto.email())
                        .password(userRequestDto.password())
                        .phone(userRequestDto.phone())
                        .userStatus(UserStatus.valueOfUserStatus(userRequestDto.userStatus()))
                        .build()
                ).toList();

        when(mockUserRepository.findAll()).thenReturn(USERS);
        for (int i = 0; i < USERS.size(); i++) {
            when(mockUserMapper.fromUser(USERS.get(i))).thenReturn(USER_RESPONSE_DTOS.get(i));
        }

        // When / Act

        List<UserResponseDto> allUsers = userService.findAllUsers();
        log.info("AllUsers: {}", allUsers);

        // Then / Assert

        assertEquals(USER_RESPONSE_DTOS, allUsers);
    }

    @Test
    @DisplayName(value = "Given: User, When: saveUser is called, Then: Verify that saveUser is called once")
    public void test_Given_User_When_SaveUserIsCalled_Then_VerifyIsCalledOnce() {

        // Given / Arrange

        final Long USER_ID = 1L;
        final User USER = User.builder()
                .id(USER_ID)
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("username@mail.com")
                .password("password")
                .phone("111111111")
                .userStatus(UserStatus.USER_STATUS_1)
                .build();
        final UserRequestDto USER_REQUEST_DTO = UserRequestDto.builder()
                .username(USER.getUsername())
                .firstName(USER.getFirstName())
                .lastName(USER.getLastName())
                .email(USER.getEmail())
                .password(USER.getPassword())
                .phone(USER.getPhone())
                .userStatus(USER.getUserStatus().getValue())
                .build();
        final UserResponseDto USER_RESPONSE_DTO = UserResponseDto.builder()
                .id(USER.getId())
                .username(USER.getUsername())
                .firstName(USER.getFirstName())
                .lastName(USER.getLastName())
                .email(USER.getEmail())
                .password(USER.getPassword())
                .phone(USER.getPhone())
                .userStatus(USER.getUserStatus().getValue())
                .build();

        when(mockUserMapper.toUser(USER_REQUEST_DTO)).thenReturn(USER);
        when(mockUserMapper.fromUser(USER)).thenReturn(USER_RESPONSE_DTO);
        when(mockUserRepository.save(USER)).thenReturn(USER);

        // When / Act

        UserResponseDto userResponseDto = userService.saveUser(USER_REQUEST_DTO);
        log.info("userResponseDto: {}", userResponseDto);

        // Then / Assert

        verify(mockUserRepository, times(1)).save(USER);
        assertEquals(USER_RESPONSE_DTO, userResponseDto);
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    public void testDeleteUserByUsername() {
    }
}
