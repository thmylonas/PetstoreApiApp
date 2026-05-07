package com.thomasmylonas.petstore_api_app.dtos.user_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRequestDto(
        @NotBlank(message = "The 'username' must not be null and must contain at least one non-whitespace character")
        @Size(min = 3, max = 15, message = "The 'username' size must be between 3 and 15 characters (included)")
        @JsonProperty(value = "username")
        String username,

        @NotBlank(message = "The 'firstName' must not be null and must contain at least one non-whitespace character")
        @JsonProperty(value = "first_name")
        String firstName,

        @NotBlank(message = "The 'lastName' must not be null and must contain at least one non-whitespace character")
        @JsonProperty(value = "last_name")
        String lastName,

        @Email(message = "The 'email' must be well-formed")
        @JsonProperty(value = "email")
        String email,

        @NotBlank(message = "The 'password' must not be null and must contain at least one non-whitespace character")
        @Size(min = 3, message = "The 'password' size must greater or equal than 3 characters")
        @JsonProperty(value = "password")
        String password,

        @JsonProperty(value = "phone")
        String phone,

        @Min(value = 0, message = "The 'user_status' must be higher or equal to 0")
        @Max(value = 1, message = "The 'number_field' must be lower or equal to 1")
        @JsonProperty(value = "user_status")
        Integer userStatus
) {
}
