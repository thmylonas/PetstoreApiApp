package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.api.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;

import java.util.List;

public interface UserService {

    UserResponseDto userLogin(String username);

    UserResponseDto userLogout(String username);

    UserResponseDto findUserByUsername(String username) throws RequestedResourceNotFoundException;

    List<UserResponseDto> findAllUsers();

    UserResponseDto saveUser(UserRequestDto userRequestDto);

    List<UserResponseDto> saveAllUsers(List<UserRequestDto> userRequestDtos);

    UserResponseDto updateUser(UserRequestDto userRequestDto, String username) throws RequestedResourceNotFoundException;

    void deleteUserByUsername(String username) throws RequestedResourceNotFoundException;
}
