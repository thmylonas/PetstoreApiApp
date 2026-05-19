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
        final UserResponseDto USER_RESPONSE_DTO = UserResponseDto.builder()
                .id(USER_ID)
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
                .firstName("First_Name")
                .lastName("Last_Name")
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
    @DisplayName(value = "When: UsernameToUpdate and UserRequestDto, When: updateUser is called, Then: userResponseDto is returned")
    public void test_When_UsernameToUpdate_And_UserRequestDto_When_UpdateUserIsCalled_Then_UserResponseDtoIsReturned() {

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
        final User USER_UPDATED = User.builder()
                .id(USER_ID)
                .username(USER_REQUEST_DTO.username())
                .firstName(USER_REQUEST_DTO.firstName())
                .lastName(USER_REQUEST_DTO.lastName())
                .email(USER_REQUEST_DTO.email())
                .password(USER_REQUEST_DTO.password())
                .phone(USER_REQUEST_DTO.phone())
                .userStatus(UserStatus.valueOfUserStatus(USER_REQUEST_DTO.userStatus()))
                .build();
        final UserResponseDto USER_RESPONSE_DTO = UserResponseDto.builder()
                .id(USER.getId())
                .username(USER_REQUEST_DTO.username())
                .firstName(USER_REQUEST_DTO.firstName())
                .lastName(USER_REQUEST_DTO.lastName())
                .email(USER_REQUEST_DTO.email())
                .password(USER_REQUEST_DTO.password())
                .phone(USER_REQUEST_DTO.phone())
                .userStatus(USER_REQUEST_DTO.userStatus())
                .build();

        when(mockUserRepository.findByUsername(USERNAME_TO_UPDATE)).thenReturn(Optional.of(USER));
        when(mockUserRepository.save(USER)).thenReturn(USER_UPDATED);
        when(mockUserMapper.fromUser(USER_UPDATED)).thenReturn(USER_RESPONSE_DTO);

        // When / Act

        UserResponseDto userResponseDto = userService.updateUser(USERNAME_TO_UPDATE, USER_REQUEST_DTO);
        log.info("userResponseDto: {}", userResponseDto);

        // Then / Assert
        assertEquals(USER_RESPONSE_DTO, userResponseDto);
    }

    @Test
    @DisplayName(value = "Given: Username, When: deleteUserByUsername is called, Then: UserService::deleteUserByUsername is called once")
    public void test_Given_Username_When_DeleteUserByUsernameIsCalled_Then_UserServiceDeleteUserByUsernameIsCalledOnce() {

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
        doNothing().when(mockUserRepository).delete(USER);

        // When / Act

        userService.deleteUserByUsername(USER.getUsername());

        // Then / Assert

        verify(mockUserRepository, times(1)).delete(USER);
    }
}
