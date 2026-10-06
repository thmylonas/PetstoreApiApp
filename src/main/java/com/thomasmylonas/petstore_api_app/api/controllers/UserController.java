package com.thomasmylonas.petstore_api_app.api.controllers;

import com.thomasmylonas.petstore_api_app.api.dtos.user_dtos.UserRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.user_dtos.UserResponseDto;
import com.thomasmylonas.petstore_api_app.api.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.api.models.ResponseSuccess;
import com.thomasmylonas.petstore_api_app.api.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(path = {"/api/v1/users"})
@RequiredArgsConstructor
@Validated
public class UserController {

    private static final String REQUEST_MAPPING = "/api/v1/users";

    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/users/{username}/login"
     *
     * @param userUsername The "userUsername"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/{username}/login"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> userLogin(@PathVariable(value = "username")
                                                     @NotBlank(message = "The 'username' must not be null and must contain at least one non-whitespace character")
                                                     @Size(min = 3, max = 15, message = "The 'username' size must be between 3 and 15 characters (included)")
                                                     String userUsername) {
        final String message = "Success: The User with username " + userUsername + " is logged-in!";
        UserResponseDto userResponseDto = userService.userLogin(userUsername);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("user_response", userResponseDto));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/users/{username}/logout"
     *
     * @param userUsername The "userUsername"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/{username}/logout"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> userLogout(@PathVariable(value = "username")
                                                      @NotBlank(message = "The 'username' must not be null and must contain at least one non-whitespace character")
                                                      @Size(min = 3, max = 15, message = "The 'username' size must be between 3 and 15 characters (included)")
                                                      String userUsername) {
        final String message = "Success: The User with username " + userUsername + " is logged-out!";
        UserResponseDto userResponseDto = userService.userLogout(userUsername);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("user_response", userResponseDto));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/users/{username}"
     *
     * @param userUsername The "userUsername"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findUserByUsername(@PathVariable(value = "username")
                                                              @NotBlank(message = "The 'username' must not be null and must contain at least one non-whitespace character")
                                                              @Size(min = 3, max = 15, message = "The 'username' size must be between 3 and 15 characters (included)")
                                                              String userUsername) {
        final String message = "Success: The Users with name " + userUsername + " are found!";
        UserResponseDto userResponseDto = userService.findUserByUsername(userUsername);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("user_by_username_response", userResponseDto));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/users"
     *
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllUsers() {
        final String message = "Success: The Users are found!";
        List<UserResponseDto> userResponseDtos = userService.findAllUsers();
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("users_response", userResponseDtos));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/users"
     *
     * @param userRequestDto The "userRequestDto"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveUser(@RequestBody @Valid UserRequestDto userRequestDto) {

        final String message = "Created: The User has been created successfully!";
        UserResponseDto savedUserResponseDto = userService.saveUser(userRequestDto);
        String savedUserUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{username}")
                .buildAndExpand(savedUserResponseDto.username())
                .toUriString();
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, savedUserUri, Map.of("saved_user_response", savedUserResponseDto));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/users/all"
     *
     * @param userRequestDtos The "userRequestDtos"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping(path = {"/all"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllUsers(@RequestBody
                                                        @NotEmpty(message = "The 'userRequestDtos' must not be null or empty")
                                                        List<@Valid UserRequestDto> userRequestDtos) {
        final String message = "Created: The Users have been created successfully!";
        List<UserResponseDto> savedUserResponseDtos = userService.saveAllUsers(userRequestDtos);
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, Map.of("saved_users_response", savedUserResponseDtos));
    }

    /**
     * Endpoint:
     * - PUT, "http://localhost:8080/api/v1/users/{id}"
     *
     * @param userUsername The "userUsername"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PutMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> updateUser(@RequestBody @Valid UserRequestDto userRequestDto,
                                                      @PathVariable(value = "username")
                                                      @NotBlank(message = "The 'username' must not be null and must contain at least one non-whitespace character")
                                                      @Size(min = 3, max = 15, message = "The 'username' size must be between 3 and 15 characters (included)")
                                                      String userUsername) {
        final String message = "Success: The User with username " + userUsername + " has been updated successfully!";
        UserResponseDto updatedUserResponseDto = userService.updateUser(userRequestDto, userUsername);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("updated_user_response", updatedUserResponseDto));
    }

    /**
     * Endpoint:
     * - DELETE, "http://localhost:8080/api/v1/users/{id}"
     *
     * @param userUsername The "userUsername"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @DeleteMapping(path = {"/{username}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> deleteUserByUsername(@PathVariable(value = "username")
                                                                @NotBlank(message = "The 'username' must not be null and must contain at least one non-whitespace character")
                                                                @Size(min = 3, max = 15, message = "The 'username' size must be between 3 and 15 characters (included)")
                                                                String userUsername) {
        final String message = "Success: The User with username " + userUsername + " has been deleted successfully!";
        userService.deleteUserByUsername(userUsername);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("message", message));
    }
}
