package com.thomasmylonas.petstore_api_app.api.services;

import com.thomasmylonas.petstore_api_app.api.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.api.entities.User;
import com.thomasmylonas.petstore_api_app.api.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.api.repositories.UserRepository;
import com.thomasmylonas.petstore_api_app.api.services.mappers.UserMapper;
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
        User user = userMapper.toUser(userRequestDto);
        User savedUser = userRepository.save(user);
        return userMapper.fromUser(savedUser);
    }

    @Override
    public List<UserResponseDto> saveAllUsers(List<UserRequestDto> userRequestDtos) {
        return userRequestDtos.stream().map(this::saveUser).toList();
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, String username) throws RequestedResourceNotFoundException {

        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The user with username " + username + " is not found!"));
        User user = userMapper.toUser(userRequestDto);

        if (StringUtils.hasLength(user.getUsername())) {
            userToUpdate.setUsername(user.getUsername());
        }
        if (StringUtils.hasLength(user.getFirstName())) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (StringUtils.hasLength(user.getLastName())) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (StringUtils.hasLength(user.getEmail())) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (StringUtils.hasLength(user.getPassword())) {
            userToUpdate.setPassword(user.getPassword());
        }
        if (StringUtils.hasLength(user.getPhone())) {
            userToUpdate.setPhone(user.getPhone());
        }
        if (Objects.nonNull(user.getUserStatus())) {
            userToUpdate.setUserStatus(user.getUserStatus());
        }
        User updatedUser = userRepository.save(user);
        return userMapper.fromUser(updatedUser);
    }

    @Override
    public void deleteUserByUsername(String username) throws RequestedResourceNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The user with username " + username + " is not found!")); // RequestedResourceNotFoundException
        userRepository.delete(user);
    }
}
