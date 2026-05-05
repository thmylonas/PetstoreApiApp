package com.thomasmylonas.petstore_api_app.dtos.user_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserRequestDto(
        @JsonProperty(value = "username")
        String username,

        @JsonProperty(value = "first_name")
        String firstName,

        @JsonProperty(value = "last_name")
        String lastName,

        @JsonProperty(value = "email")
        String email,

        @JsonProperty(value = "password")
        String password,

        @JsonProperty(value = "phone")
        String phone,

        @JsonProperty(value = "user_status")
        String userStatus
) {
}
