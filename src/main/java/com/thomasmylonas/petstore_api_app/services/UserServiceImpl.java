package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.entities.User;
import com.thomasmylonas.petstore_api_app.enums.UserStatus;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.UserRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto userLogin(String username) {
        return null;
    }

    @Override
    public UserResponseDto userLogout(String username) {
        return null;
    }

    @Override
    public UserResponseDto findUserByUsername(String username) throws RequestedResourceNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The user with username " + username + " is not found!"));
        return userMapper.fromUser(user);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::fromUser).toList();
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User savedUser = userRepository.save(userMapper.toUser(userRequestDto));
        return userMapper.fromUser(savedUser);
    }

    @Override
    public List<UserResponseDto> saveAllUsers(List<UserRequestDto> userRequestDtos) {
        return userRequestDtos.stream().map(this::saveUser).toList();
    }

    @Override
    public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) throws RequestedResourceNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The user with username " + username + " is not found!"));

        if (StringUtils.hasLength(userRequestDto.username())) {
            user.setUsername(userRequestDto.username());
        }
        if (StringUtils.hasLength(userRequestDto.firstName())) {
            user.setFirstName(userRequestDto.firstName());
        }
        if (StringUtils.hasLength(userRequestDto.lastName())) {
            user.setLastName(userRequestDto.lastName());
        }
        if (StringUtils.hasLength(userRequestDto.email())) {
            user.setEmail(userRequestDto.email());
        }
        if (StringUtils.hasLength(userRequestDto.password())) {
            user.setPassword(userRequestDto.password());
        }
        if (StringUtils.hasLength(userRequestDto.phone())) {
            user.setPhone(userRequestDto.phone());
        }
        if (Objects.nonNull(userRequestDto.userStatus())) {
            user.setUserStatus(UserStatus.valueOfUserStatus(userRequestDto.userStatus()));
        }
        User updatedUser = userRepository.save(user);
        return userMapper.fromUser(updatedUser);
    }

    @Override
    public void deleteUserByUsername(String username) throws RequestedResourceNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The user with username " + username + " is not found!"));
        userRepository.delete(user);
    }
}
