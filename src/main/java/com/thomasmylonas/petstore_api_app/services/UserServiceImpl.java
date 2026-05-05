package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto userLogin(String username) {
        return null;
    }

    @Override
    public UserResponseDto userLogout(String username) {
        return null;
    }

    @Override
    public UserResponseDto findUserByUsername(String username) {
        return null; //userRepository.findByUsername(username).orElseThrow(()->new RequestedResourceNotFoundException(""));
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return List.of();
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        return null; //userRepository.save();
    }

    @Override
    public List<UserResponseDto> saveAllUsers(List<UserRequestDto> userRequestDtos) {
        return List.of();
    }

    @Override
    public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) throws RequestedResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteUserByUsername(String username) throws RequestedResourceNotFoundException {

    }
}
