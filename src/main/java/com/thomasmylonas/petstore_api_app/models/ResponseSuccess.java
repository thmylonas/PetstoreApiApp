package com.thomasmylonas.petstore_api_app.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseSuccess(
        @JsonProperty(value = "timestamp")
        LocalDateTime timestamp,

        @JsonProperty(value = "status_code")
        String statusCode,

        @JsonProperty(value = "message")
        String message,

        @JsonProperty(value = "path")
        String path, // "request URL"

        @JsonProperty(value = "data")
        Map<String, ?> data // The resource to be returned
) {
}
