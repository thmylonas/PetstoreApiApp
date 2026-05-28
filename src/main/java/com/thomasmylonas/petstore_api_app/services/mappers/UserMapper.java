package com.thomasmylonas.petstore_api_app.services.mappers;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.entities.User;
import com.thomasmylonas.petstore_api_app.enums.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponseDto fromUser(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .userStatus(user.getUserStatus().getValue())
                .build();
    }

    public User toUser(UserRequestDto userRequestDto) {
        return User.builder()
                .username(userRequestDto.username())
                .firstName(userRequestDto.firstName())
                .lastName(userRequestDto.lastName())
                .email(userRequestDto.email())
                .password(userRequestDto.password())
                .phone(userRequestDto.phone())
                .userStatus(UserStatus.valueOfUserStatus(userRequestDto.userStatus()))
                .build();
    }
}
