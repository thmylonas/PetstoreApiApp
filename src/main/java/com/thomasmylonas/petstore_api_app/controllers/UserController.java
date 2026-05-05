package com.thomasmylonas.petstore_api_app.controllers;

import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseSuccess;
import com.thomasmylonas.petstore_api_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final String REQUEST_MAPPING = "/api/v1/users";

    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    @GetMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> userLogin(@PathVariable(value = "username") String username) { // "http://localhost:8080/api/v1/users/{id}"
        final String message = "Success: The User with username " + username + " is logged-in!";
        UserResponseDto userResponseDto = userService.userLogin(username);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("user_response", userResponseDto));
    }

    @GetMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> userLogout(@PathVariable(value = "username") String username) { // "http://localhost:8080/api/v1/users/{id}"
        final String message = "Success: The User with username " + username + " is logged-out!";
        UserResponseDto userResponseDto = userService.userLogout(username);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("user_response", userResponseDto));
    }

    @GetMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findUserByUsername(@PathVariable(value = "username") String username) {
        final String message = "Success: The Users with name " + username + " are found!";
        UserResponseDto userResponseDto = userService.findUserByUsername(username);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("user_by_username_response", userResponseDto));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllUsers() { // "http://localhost:8080/api/v1/users"
        final String message = "Success: The Users are found!";
        List<UserResponseDto> userResponseDtos = userService.findAllUsers();
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("users_response", userResponseDtos));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveUser(@RequestBody UserRequestDto userRequestDto) { // "http://localhost:8080/api/v1/users"

        final String message = "Created: The User has been created successfully!";
        UserResponseDto savedUserResponseDto = userService.saveUser(userRequestDto);
        String savedUserUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{username}")
                .buildAndExpand(savedUserResponseDto.username())
                .toUriString();
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, savedUserUri, Map.of("saved_user_response", savedUserResponseDto));
    }

    @PostMapping("/all")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllUsers(@RequestBody List<UserRequestDto> userRequestDtos) { // "http://localhost:8080/api/v1/users/all"
        final String message = "Created: The Users have been created successfully!";
        List<UserResponseDto> savedUserResponseDtos = userService.saveAllUsers(userRequestDtos);
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, Map.of("saved_users_response", savedUserResponseDtos));
    }

    @PutMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> updateUser(@PathVariable(value = "username") String username, @RequestBody UserRequestDto userRequestDto) { // "http://localhost:8080/api/v1/users/{id}"
        final String message = "Success: The User with username " + username + " has been updated successfully!";
        UserResponseDto updatedUserResponseDto = userService.updateUser(username, userRequestDto);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("updated_user_response", updatedUserResponseDto));
    }

    @DeleteMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> deleteUserByUsername(@PathVariable(value = "username") String username) { // "http://localhost:8080/api/v1/users/{id}"
        final String message = "Success: The User with username " + username + " has been deleted successfully!";
        userService.deleteUserByUsername(username);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("message", message));
    }
}
